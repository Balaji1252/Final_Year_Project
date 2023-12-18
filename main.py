from fastapi import FastAPI, HTTPException, Request  # importing the required modules and classes, request and jsonify are from the fastapi framework-handle API calls(request and response)
from fastapi.responses import JSONResponse  # JSONResponse is a class that helps you create HTTP responses with JSON content
import re  # regular expression
from textblob import TextBlob  # this library is used for sentimental analysis
import smtplib  # used for handling SMTP
from email.mime.text import MIMEText  # multipurpose internet mail extensions-sending text mails(used to create the body of the mail)

app = FastAPI()  # creates an instance of fastAPI application instance->app


def classify_formality(user_input):  # This function is used to find the sentimental analysis of the input from the user
    blob = TextBlob(user_input)
    sentiment_score = blob.sentiment.polarity

    if sentiment_score >= 0.2:
        return 'Professional'
    elif sentiment_score <= -0.2:
        return 'Informal'
    else:
        return 'Formal'


def classify(user_input):  # classify the input as ppt , image based on the keywords(defining the keywords and its categories)
    keywords = {
        'ppt': 'Presentation',
        'image': 'Image',
        'resume': 'Resume',
        'article': 'Article',
        'picture': 'Picture',
        'video': 'Video'           
        #Add more keywords 
    }
    words = re.findall(r'\b\w+\b', user_input.lower())  # Tokenizing user input(find all the occurrences of the specific pattern(re)) r->raw string, \b are the boundary anchors, w->character class matches the words(one or more), findall is used to find all the occurrences of the re pattern in the string and returns the matches in the list

    for word in words:  # Checking for keywords and return the category
        if word in keywords:
            return keywords[word]

    formality_result = classify_formality(user_input)  # here, if no keyword is found it just performs sentimental analysis
    return f'text'



@app.post('/classify')  # This line defines a new route in your FastAPI application using a decorator.
async def classify_input(request: Request):  # asynchronous function that takes a single parameter - request.
    data = await request.json() # awaits the completion of the asynchronous request.json() method.
    user_input = data['inputText']  # Assuming the user input is sent as JSON object
    emailId=data["emailId"]
    result = classify(user_input)  # function call-> classify the user input
    if result == "text":
        result='0'
    elif result == "Article":
        result='1'
    elif result == "Presentation":
        result='2'

    sentiment_result = classify_formality(user_input)  # function call, sentimental analysis result

    send_email(user_input, emailId,result, sentiment_result)  # function call sending the email
    return JSONResponse(content={'classification': result, 'sentiment': sentiment_result})  # This line constructs and returns a JSON response using the JSONResponse class

def send_email(user_input, emailId,result, sentiment_result):  # fun def and the information required for sending mail
    subject = emailId
    if result!=0:
        body = f"{result}{user_input} in {sentiment_result} tone"
    else:
        body = f"{result}{user_input}"
    sender_email = "infinimindai@gmail.com"
    receiver_email = "thehackash@outlook.com"
    smtp_server = "smtp.gmail.com"
    smtp_port = 587
    smtp_username = "inifinimindai@gmail.com"
    smtp_password = "aahdkcpnebnsghrh"

    message = MIMEText(body)  # These lines create an ins tance of mimetext with the body as the email content and sets the subject, from, to.
    message['Subject'] = subject
    message['From'] = sender_email
    message['To'] = receiver_email

    with smtplib.SMTP(smtp_server, smtp_port) as server:  # This part establishes an SMTP connection using a specified SMTP server and port.
        server.starttls()  # It enables Transport Layer security to secure the connection
        server.login(smtp_username, smtp_password)  # We should provide a username and password, and then it gets logs(login)
        server.sendmail(sender_email, [receiver_email], message.as_string())  # sends the mail, and the msg is converted to a string
