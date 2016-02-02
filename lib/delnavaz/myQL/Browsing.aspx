<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Browsing.aspx.cs" Inherits="Browsing" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:GridView ID="GridView1" runat="server" AllowPaging="True" 
            DataSourceID="XmlDataSourceOffers">
            <Columns>
                <asp:CommandField ShowSelectButton="True" />
            </Columns>
        </asp:GridView>
        <asp:XmlDataSource ID="XmlDataSourceOffers" runat="server" 
            DataFile="~/sample1.xml"></asp:XmlDataSource>
    
    </div>
    </form>
</body>
</html>
