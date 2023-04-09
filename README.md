# Dmoney-MFS-Site-Automation-With-Salenium


This is Simple automation of an Dmoney MFS Site.
I have used TestNg as a test framework and faker library
to generate random users. I Have automate all the major functionality 
Like 1.Send Money 2.Payment 3.Withdrwal 4.Deposit 


## Author

- [@Jamil-kawsher1](https://www.github.com/Jamil-kawsher1)
## Automation Video
https://user-images.githubusercontent.com/42008531/230767609-35399c97-32c4-4539-8e31-161ba99f555a.mp4





## How to run this project

- Clone This project
- open build.gradle file in IntelliJ IDEA
- Then Hit the follwing command in terminal

```bash
  allure generate allure-results --clean -o allure-report
  allure serve allure-results
```
## Tools and Tech
- IntelliJ IDEA
- Selenium with TestNG
- Allure
- Gradle


## Test Case:
https://docs.google.com/spreadsheets/d/1XRsdEiDuIZh1Pt00d-sPeOD4dE5LaGtN/edit?usp=sharing&ouid=105413695096846736430&rtpof=true&sd=true



# Allure report Screenshots
![DmoneyAutomation1](https://user-images.githubusercontent.com/42008531/230767348-9c260653-0d57-4643-b2db-20bc0ee17ed2.jpg)
![DmoneyAutomation2](https://user-images.githubusercontent.com/42008531/230767356-585ce118-6257-44a7-86f4-06b9886043e3.jpg)







## Scenerio

URL: https://dmoney-web.vercel.app/ 

1. Login as admin
creds: salman@roadtocareer.net 1234 
2. create an agent and customer. Assert agent and customer has created 
3. search by the newly created customer phone number 
4. Update customer password. Assert update is successful 
5. Now login to system account
creds: system@roadtocareer.net 1234 
6. Deposit 2000 tk to the agent you just created and assert deposit successful 
7. Now login to the agent account and deposit to the customer account you just created 
8. Assert successful deposit message 
9. Now login to the customer account and check if statement is showing 
10. Now send 100 tk to another customer account 
11 Check again the customer statement that 105 tk is debited. Assert with the expected total amount. 
12. Now withdraw tk 500 and assert the expected current balance after successful withdrawal 
13. Now go to customer statement and assert trnx id is found in statement 
14. Now payment to a merchant 100 tk and assert which current balance is expected
Creds: 01686606905 1234
15. Now login to the admin again, go to transaction menu and search by your customer mobile number to check that payment is shown in transaction list
