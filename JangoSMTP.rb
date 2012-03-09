=begin

 JangoSMTP.rb

 This file demonstrates sending an email from a Ruby program using JangoSMTP.

 This file requires the mail gem, which can be obtained at https://github.com/mikel/mail

 The mail gem can be installed with the command 'gem install mail'

=end

require 'mail'

Mail.defaults do
  delivery_method :smtp, { :address   => "relay.jangosmtp.net",
                           :port      => 25,
                           :domain    => "YourDomain.com",
                           :user_name => "Your Username",
                           :password  => "Your Password",
                           :authentication => 'plain',
                           :enable_starttls_auto => true }
end

# Set up your email
# Note that the from address must be stored in your list of from addresses
# To modify your from addresses, see Settings > Advanced > From Addresses
mail = Mail.deliver do
  to 'To@EmailAddress.com'
  from 'From Name <FromAddress@YourDomain.com>'
  subject 'This is the subject!'
  text_part do
    body 'This plain text email was sent from a Ruby application!'
  end
  html_part do
    content_type 'text/html; charset=UTF-8'
    body '<b>This HTML email was sent from a Ruby application!</b>'
  end
end