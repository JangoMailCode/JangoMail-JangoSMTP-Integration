<?php
// JangoSMTP.php
// This example shows how to send an email using JangoSMTP in a php script.
// The Swift Mailer PHP library is required. It can be downloaded here: http://swiftmailer.org/download

// Set up a simple custom error handler.
// This is just to allow easier debugging.
set_error_handler("customError"); 

// include the Swift Mailer code
include_once "Swift/lib/swift_required.php";
 
// Set up the plain text body of the email
$text = "This is a plain text message sent from a PHP script!\n";
// set up the HTML portion of the email
$html = <<<EOM
<html>
  <head></head>
  <body>
    <p>This is an HTML message sent from a PHP script!<br/>
    </p>
  </body>
</html>
EOM;
 
// set the from address
// this must be an address stored in your JangoSMTP settings
$from = array('YourEmail@YourDomain.com' => 'Your Name');
// add recipients
$to = array(
  'ToAddress@Domain.com'=>'Recipient Name'
);
// set the subject of the email
$subject = 'JangoSMTP Transactional Email from PHP';
 
// your JangoSMTP login credentials
$username = 'Your JangoSMTP Username';
$password = 'Your JangoSMTP Password';
 
// setup Swift mailer parameters
$transport = Swift_SmtpTransport::newInstance('relay.jangosmtp.net', 25);
$transport->setUsername($username);
$transport->setPassword($password);
$swift = Swift_Mailer::newInstance($transport);
 
// set up the email message in swift
$message = new Swift_Message($subject);
// set the body of the email
$message->setFrom($from);
$message->setBody($html, 'text/html');
$message->setTo($to);
$message->addPart($text, 'text/plain');
 
// send the message through jangosmtp
if ($recipients = $swift->send($message, $failures))
{
	// inform the user if sending succeeds
	echo 'Email sent to '.$recipients.' users';
}
else
{
	// inform the user that something went wrong
	echo "Error Sending Email - ";
	print_r($failures);
}

// this custom error function will allow you to debug this script even if you have disabled error messages
function customError($errno, $errstr)
{
	echo "<b>Error:</b> [$errno] $errstr<br />";
} 

?>