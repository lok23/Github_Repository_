#ps1b

annual_salary = int(input("Enter your annual salary:"))
portion_saved = float(input("Enter the percent of your salary to save, as a decimal:"))
total_cost = int(input("Enter the cost of your dream home:"))
semi_annual_raise = float(input("Enter the semi-annual raise, as a decimal:"))

portion_down_payment = 0.25 * total_cost
current_savings = 0
monthly_salary = annual_salary / 12


months = 0
count = 0 #Tracking if 6 months have passed
while current_savings < portion_down_payment:
    current_savings += current_savings * 0.04 / 12
    current_savings += monthly_salary * portion_saved
    if count == 6:
        monthly_salary += monthly_salary * semi_annual_raise #every 6 months, our salary goes up by a semi_annual_raise
        count = 0
    months += 1
    count += 1
print(months)
