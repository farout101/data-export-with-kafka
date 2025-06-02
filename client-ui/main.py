import streamlit as st
from home import home_page
from download import download_page

st.set_page_config(page_title="Data Export with Kafka", layout="wide")

# Sidebar for navigation
page = st.sidebar.radio("Navigate", ["Home", "Download"])

if page == "Home":
    home_page()
elif page == "Download":
    download_page()