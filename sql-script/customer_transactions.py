from datetime import datetime, timedelta
import random

# Reuse values
transaction_types = ['deposit', 'withdrawal', 'transfer']
currencies = ['MMK', 'USD', 'EUR']
locations = ['Yangon', 'Mandalay', 'Naypyidaw', 'Taunggyi', 'Bago']
statuses = ['completed', 'pending', 'failed']
base_time = datetime(2025, 5, 1, 8, 0, 0)

# Generate 1000 mock transactions
insert_lines = []
for _ in range(1000):
    customer_id = random.randint(1000, 1100)
    account_id = random.randint(2000, 2100)
    transaction_type = random.choice(transaction_types)
    amount = round(random.uniform(10000, 1000000), 2)
    currency = random.choice(currencies)
    transaction_time = base_time + timedelta(minutes=random.randint(0, 20000))
    location = random.choice(locations)
    status = random.choice(statuses)
    
    insert_line = f"({customer_id}, {account_id}, '{transaction_type}', {amount}, '{currency}', '{transaction_time.strftime('%Y-%m-%d %H:%M:%S')}', '{location}', '{status}')"
    insert_lines.append(insert_line)

# Combine into one SQL statement
insert_sql = "INSERT INTO customer_transactions (customer_id, account_id, transaction_type, amount, currency, transaction_time, location, status)\nVALUES\n" + ",\n".join(insert_lines) + ";"

# Save to .sql file
file_path = "./data/insert_mock_customer_transactions.sql"
with open(file_path, "w") as f:
    f.write(insert_sql)

file_path