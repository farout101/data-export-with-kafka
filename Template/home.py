import streamlit as st
import requests
from datetime import datetime

SERVER_URL = "http://your-server-url/export"  # Replace with your actual server URL

def home_page():
    st.title("Data Export - Home")

    # File format selection
    file_format = st.selectbox("Select file format", ["CSV", "JSON", "Excel"])

    # Transaction record selection
    transaction_type = st.selectbox("Select transaction record", ["Sales", "Purchases", "Refunds"])

    # Time period selection
    st.subheader("Select Time Period")
    date = st.date_input("Select date", value=datetime.now())
    col1, col2 = st.columns(2)
    with col1:
        hour_display = st.number_input("Hour (1-24)", min_value=1, max_value=24, value=1)
    with col2:
        minute_display = st.number_input("Minute (1-60)", min_value=1, max_value=60, value=1)

    hour = hour_display
    minute = minute_display

    year = date.year
    month = date.month
    day = date.day

    if st.button("Export"):
        params = {
            "file_format": file_format,
            "transaction_type": transaction_type,
            "year": int(year),
            "month": int(month),
            "day": int(day),
            "hour": int(hour),
            "minute": int(minute)
        }
        try:
            response = requests.post(SERVER_URL, json=params)
            if response.status_code == 200:
                st.success("Export request sent successfully!")
            else:
                st.error(f"Failed to send export request: {response.text}")
        except Exception as e:
            st.error(f"Error: {e}")