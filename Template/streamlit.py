import streamlit as st

st.title("Data Export with Kafka")
st.write("Welcome to the Data Export with Kafka Streamlit app!")

# Example input
topic = st.text_input("Kafka Topic", "my_topic")
message = st.text_area("Message to Send")