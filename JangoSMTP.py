####################################################################
# JangoSMTP.py
# downloaded from jangomail.com/Tutorials/JangoSMTP.py
# This example shows how to send email through the jangosmtp service
# from within a python program.
####################################################################
 
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
 
# This from-address must be stored in your JangoSMTP settings 
FromAddress = "YourEmail@Address.com"

# set the recipient
ToAddress = "Recipient@Domain.com"
 
# create the message object
msg = MIMEMultipart('alternative')
msg['Subject'] = "JangoSMTP from Python Test"
msg['From'] = FromAddress
msg['To'] = ToAddress
 
# Create the body of the message (a plain-text and an HTML version).
text = "This is a plain text email sent from a python program!"
html = """\
<html>
  <head></head>
  <body>
    <p>
      This is an html email sent from a <b>python</b> program!
    </p>
  </body>
</html>
"""
 
# JangoSMTP credentials
username = "Your JangoSMTP Username"
password = "Your JangoSMTP Password"

# Record the MIME types of both parts - text/plain and text/html.
part1 = MIMEText(text, 'plain')
part2 = MIMEText(html, 'html')
 
# Attach parts into message container.
msg.attach(part1)
msg.attach(part2)
 
# Open a connection to the JangoSMTP server
s = smtplib.SMTP('relay.jangosmtp.net', 25)
 
# Authenticate
s.login(username, password)
 
# sendmail function takes 3 arguments: sender's address, recipient's address
# and message to send - here it is sent as one string.
s.sendmail(FromAddress, ToAddress, msg.as_string())
 
s.quit()