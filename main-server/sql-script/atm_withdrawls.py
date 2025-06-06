from datetime import datetime, timedelta
import random


# Constants for ATM withdrawals
atm_ids = [f'ATM{str(i).zfill(3)}' for i in range(1, 21)]
locations = ['Yangon Downtown', 'Mandalay Central', 'Insein', 'Botahtaung', 'Hledan', 'Shwebo']
statuses = ['success', 'failed', 'timeout']
base_time = datetime(2025, 5, 1, 6, 0, 0)

# Generate 1000 mock ATM withdrawal records
atm_insert_lines = []
for _ in range(1000):
    atm_id = random.choice(atm_ids)
    card_number_hash = ''.join(random.choices('abcdef0123456789', k=64))
    withdrawal_amount = round(random.uniform(10000, 500000), 2)
    transaction_time = base_time + timedelta(minutes=random.randint(0, 20000))
    atm_location = random.choice(locations)
    balance_remaining = round(random.uniform(10000, 1000000), 2)
    transaction_status = random.choice(statuses)
    
    insert_line = f"('{atm_id}', '{card_number_hash}', {withdrawal_amount}, '{transaction_time.strftime('%Y-%m-%d %H:%M:%S')}', '{atm_location}', {balance_remaining}, '{transaction_status}')"
    atm_insert_lines.append(insert_line)

# Combine into one SQL statement
atm_insert_sql = "INSERT INTO atm_withdrawals (atm_id, card_number_hash, withdrawal_amount, transaction_time, atm_location, balance_remaining, transaction_status)\nVALUES\n" + ",\n".join(atm_insert_lines) + ";"

# Save to .sql file
atm_file_path = "./data/insert_mock_atm_withdrawals.sql"
with open(atm_file_path, "w") as f:
    f.write(atm_insert_sql)

atm_file_path
