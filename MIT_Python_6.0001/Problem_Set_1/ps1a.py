#ps1a

annual_salary = int(input("Enter your annual salary:"))
portion_saved = float(input("Enter the percent of your salary to save, as a decimal:"))
total_cost = int(input("Enter the cost of your dream home:"))

portion_down_payment = 0.25 * total_cost
current_savings = 0
monthly_salary = annual_salary / 12


months = 0
while current_savings < portion_down_payment:
    current_savings += (current_savings * 0.04 / 12) #add interest to our current savings
    current_savings += (monthly_salary * portion_saved) #add a portion of our monthly salary to current savings
    months += 1
print(months)


