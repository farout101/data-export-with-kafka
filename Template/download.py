import streamlit as st
import requests

DOWNLOAD_URL = "http://your-server-url/download"  # Replace with your actual download endpoint

def download_page():
    st.title("Download Exported File")

    # Placeholder for server-triggered message
    message_placeholder = st.empty()
    file_downloaded = False

    # Polling endpoint (simulate server trigger)
    if st.button("Check for Exported File"):
        try:
            response = requests.get(DOWNLOAD_URL)
            if response.status_code == 200 and response.headers.get("Content-Type") != "application/json":
                # Assume file is ready for download
                file_name = response.headers.get("Content-Disposition", "attachment; filename=exported_file").split("filename=")[-1]
                st.success("File is ready for download!")
                st.download_button(
                    label="Download File",
                    data=response.content,
                    file_name=file_name,
                    mime=response.headers.get("Content-Type", "application/octet-stream")
                )
                file_downloaded = True
            else:
                # Show server message if available
                msg = response.json().get("message", "No file available yet.")
                message_placeholder.info(msg)
        except Exception as e:
            st.error(f"Error: {e}")

    if not file_downloaded:
        st.info("No file available for download yet. Please check again later.")