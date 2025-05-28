import streamlit as st
import requests
from datetime import datetime, time

SERVER_URL = "http://localhost:8080/api/v1/request"  # Replace with your actual server URL

def home_page():
    st.title("Data Export - Home")

    # File format selection
    file_format = st.selectbox("Select file format", ["CSV", "JSON", "XLSX"])

    # Transaction record selection
    transaction_type = st.selectbox("Select transaction record", ["Credit", "Debit", "All"])
    transaction_type = transaction_type.upper()

    # Time period selection
    st.subheader("Select Start and End Time")
    col1, col2 = st.columns(2)
    with col1:
        start_date = st.date_input("Start date", value=datetime.now())
        start_time = st.time_input("Start time", value=time(0, 0))
    with col2:
        end_date = st.date_input("End date", value=datetime.now())
        end_time = st.time_input("End time", value=time(23, 59))

    if st.button("Export"):
        # Combine date and time for start and end
        start_dt = datetime.combine(start_date, start_time)
        end_dt = datetime.combine(end_date, end_time)

        # Generate a unique export_Id using timestamp (reduced to fit Java int size)
        export_id = int(datetime.now().strftime('%m%d%H%M%S'))  # MMddHHmmss, always fits in 32-bit int

        params = {
            "export_id": export_id,
            "file_format": file_format,
            "transaction_type": transaction_type,
            "start_datetime": start_dt.strftime("%Y-%m-%dT%H:%M:%S"),
            "end_datetime": end_dt.strftime("%Y-%m-%dT%H:%M:%S")
        }
        try:
            response = requests.post(SERVER_URL, json=params)
            if response.status_code == 200:
                st.success("Export request sent successfully!")
            else:
                st.error(f"Failed to send export request: {response.text}")
        except Exception as e:
            st.error(f"Error: {e}")