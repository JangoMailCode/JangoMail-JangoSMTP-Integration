<%@ Page Language="C#" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script runat="server">
    /*
        JangoMail_API.aspx

        For this example to work you must add the JangoMail API web reference to your web site.
        You can do this by right clicking on your project in the solution explorer, then click on "Add Service Reference".
        Under Address, enter http://api.jangomail.com/api.asmx?WSDL and click Go
        Then change the namespace to JangoMail and click OK.
    */

    protected void Page_Load(object sender, EventArgs e)
    {
        GetGroupList();
    }

    void GetGroupList()
    {
        try
        {
            // first create the JangoMail soap client object
            JangoMail.JangoMailSoapClient j = new JangoMail.JangoMailSoapClient("JangoMailSoap");

            // then call the getlist method and display the results in our result div
            Result.InnerHtml = j.Groups_GetList_String("Your JangoMail/JangoSMTP Username", "Your JangoMail/JangoSMTP Password", "<br/>", " - ", "");
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
