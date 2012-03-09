<%@ Page Language="C#" %>
<%@ Import namespace="System.Net.Mime" %>
<%@ Import namespace="System.Net.Mail" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script runat="server">
    protected void Page_Load(object sender, EventArgs e)
    {
        SendTestEmail();
    }

    // this routine demonstrates sending smtp mail using JangoSMTP in ASP.Net
    void SendTestEmail()
    {
        try
        {
            MailMessage mailMsg = new MailMessage();

            // To
            mailMsg.To.Add(new MailAddress("ToAddress@Domain.com", "Recipient Name"));

            // set the from address
            // this must be stored in your from addresses
            mailMsg.From = new MailAddress("YourEmail@YourDomain.com", "Your Name");

            // set the subject and message portions of the email
            mailMsg.Subject = "Test Email Subject";
            string text = "This plain text email was sent from an ASP.Net script.";
            string html = @"<p>This html email was sent from an ASP.Net script.</p>";
            mailMsg.AlternateViews.Add(AlternateView.CreateAlternateViewFromString(text, null, MediaTypeNames.Text.Plain));
            mailMsg.AlternateViews.Add(AlternateView.CreateAlternateViewFromString(html, null, MediaTypeNames.Text.Html));

            // set up the SMTP client with relay.jangosmtp.net
            SmtpClient smtpClient = new SmtpClient("relay.jangosmtp.net", Convert.ToInt32(25));
            System.Net.NetworkCredential credentials = new System.Net.NetworkCredential("Your JangoSMTP Username", "Your JangoSMTP Password");
            smtpClient.Credentials = credentials;

            smtpClient.Send(mailMsg);

            Result.InnerHtml = "Mail sent!";
        }
        catch (Exception ex)
        {
            Response.Write(ex.Message);
        }
    }
</script>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div id="Result" runat="server">
    
    </div>
    </form>
</body>
</html>
