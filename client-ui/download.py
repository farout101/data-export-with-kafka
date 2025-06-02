import streamlit as st
import requests

STATUS_URL = "http://localhost:8080/api/v1/client/export/status"
MARK_DOWNLOADED_URL = "http://localhost:8080/export/mark-downloaded"

def download_page():
    st.title("Exported Files List")

    # ✅ Initialize session state here
    if "downloaded_ids" not in st.session_state:
        st.session_state.downloaded_ids = set()

    try:
        response = requests.get(STATUS_URL)
        if response.status_code == 200:
            records = response.json()

            if not records:
                st.info("No exported files available.")
                return

            for record in records:
                export_id = record["exportId"]
                file_name = record["fileName"].split("/")[-1]
                file_url = record["downloadUrl"]

                col1, col2 = st.columns([4, 1])
                with col1:
                    st.write(f"📄 `{file_name}` - {record['message']}")

                with col2:
                    file_resp = requests.get(file_url)
                    if file_resp.status_code == 200:
                        if st.download_button(
                            label="⬇️ Download",
                            data=file_resp.content,
                            file_name=file_name,
                            mime="application/json",
                            key=f"dl-{export_id}"
                        ):
                            # 👉 Save to session state to mark later
                            st.session_state.downloaded_ids.add(export_id)
                    else:
                        st.warning("Could not fetch file")
        else:
            st.error("Failed to fetch records")
    except Exception as e:
        st.error(f"Error: {e}")

    # ✅ Post-download marking (after download)
    if st.session_state.downloaded_ids:
        for eid in list(st.session_state.downloaded_ids):
            try:
                mark_resp = requests.post(f"{MARK_DOWNLOADED_URL}/{eid}")
                if mark_resp.status_code == 200:
                    st.session_state.downloaded_ids.remove(eid)
            except Exception as e:
                st.warning(f"Could not mark export {eid} as downloaded")
