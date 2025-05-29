import random
from datetime import datetime, timedelta

# Constants for interbank transfers
bank_codes = ['KBZ', 'AYA', 'CB', 'UAB', 'YOMA', 'AGD']
currencies = ['MMK', 'USD', 'SGD']
reasons = ['Salary payment', 'Invoice settlement', 'Loan repayment', 'Family support', 'Online shopping']
statuses = ['completed', 'failed', 'processing']
base_time = datetime(2025, 5, 1, 9, 0, 0)

# Generate 1000 mock interbank transfer records
transfer_insert_lines = []
for _ in range(1000):
    sender_bank = random.choice(bank_codes)
    receiver_bank = random.choice([b for b in bank_codes if b != sender_bank])
    sender_account = f"{random.randint(10000000, 99999999)}"
    receiver_account = f"{random.randint(10000000, 99999999)}"
    amount = round(random.uniform(50000, 10000000), 2)
    currency = random.choice(currencies)
    transfer_reason = random.choice(reasons)
    transaction_time = base_time + timedelta(minutes=random.randint(0, 20000))
    status = random.choice(statuses)

    insert_line = f"('{sender_bank}', '{receiver_bank}', '{sender_account}', '{receiver_account}', {amount}, '{currency}', '{transfer_reason}', '{transaction_time.strftime('%Y-%m-%d %H:%M:%S')}', '{status}')"
    transfer_insert_lines.append(insert_line)

# Combine into one SQL statement
transfer_insert_sql = "INSERT INTO interbank_transfers (sender_bank_code, receiver_bank_code, sender_account, receiver_account, amount, currency, transfer_reason, transaction_time, status)\nVALUES\n" + ",\n".join(transfer_insert_lines) + ";"

# Save to .sql file
transfer_file_path = "./data/insert_mock_interbank_transfers.sql"
with open(transfer_file_path, "w") as f:
    f.write(transfer_insert_sql)

transfer_file_path
