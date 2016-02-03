<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Interface1.aspx.cs" Inherits="Interface1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>QQL framework</title>
    <style type="text/css">
        .style1
        {
            height: 122px;
        }
        .style2
        {
            width: 118px;
        }
        .style3
        {
            height: 122px;
            width: 118px;
        }
        .style4
        {
            width: 110px;
        }
        .style5
        {
            height: 122px;
            width: 110px;
        }
    </style>
    
<script language="javascript" type="text/javascript">

            function BrowsingPage(strField)
            {
                        window.open('Browsing.aspx', 'BrowsingPopup', 'width=250,height=190,resizable=yes'); //;?field=' + strField, 'calendarPopup', 'width=250,height=190,resizable=yes');
            }

</script>

    
</head>

<body bgcolor="#cccccc">
    <form id="form1" runat="server">
    <div>
    <table id="tbl1" border="0" align="center" bgcolor="#CCCCCC" cellpadding="10" 
            cellspacing="1" frame="box" 
            style="border: thin ridge #00FFFF; font-family: Tahoma; font-size: small; font-weight: normal; font-style: normal; font-variant: normal; color: #000000" 
            width="95%">
    <tr>
        <td class="style2">
            <asp:CheckBox ID="chAvailability" runat="server" Text="availability" 
                AutoPostBack="True" oncheckedchanged="chAvailability_CheckedChanged" />
        </td>
        <td class="style4">
            <asp:RadioButtonList ID="RBA" runat="server" AutoPostBack="True" 
                Enabled="False" onselectedindexchanged="RBA_SelectedIndexChanged" 
                Height="114px">
                <asp:ListItem Value="0">single value</asp:ListItem>
                <asp:ListItem Value="1">range value</asp:ListItem>
                <asp:ListItem Value="2">fuzzy value</asp:ListItem>
            </asp:RadioButtonList>
        </td>
        <td>
        
            <asp:TextBox ID="txtSingleA" runat="server" Visible="False"></asp:TextBox>
            <br />
            <asp:Label ID="lblfrom1" runat="server" Text="from:" Visible="False"></asp:Label>
&nbsp;<asp:TextBox ID="txtRangeFromA" runat="server" Visible="False"></asp:TextBox>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Label ID="lblto1" runat="server" Text="to:" Visible="False"></asp:Label>
&nbsp;<asp:TextBox ID="txtRangeToA" runat="server" Visible="False"></asp:TextBox>
            <asp:HyperLink ID="linkBrowseA" runat="server" href="javascript:;" onclick="BrowsingPage('Form1.txtEventDate');" title="Browse the possible values" visible="false">Browsing</asp:HyperLink>
&nbsp;<asp:RadioButtonList ID="RBFuzzyA" runat="server" Visible="False">
                <asp:ListItem>high</asp:ListItem>
                <asp:ListItem>medium</asp:ListItem>
                <asp:ListItem>low</asp:ListItem>
            </asp:RadioButtonList>
        </td>
    </tr>
    <tr>
        <td class="style2">
            <asp:CheckBox ID="chPrice" runat="server"  Text="price" AutoPostBack="True" 
                oncheckedchanged="chPrice_CheckedChanged" />
        </td>
        <td class="style4">
            <asp:RadioButtonList ID="RBP" runat="server" AutoPostBack="True" 
                Enabled="False" onselectedindexchanged="RBP_SelectedIndexChanged">
                <asp:ListItem Value="0">single value</asp:ListItem>
                <asp:ListItem Value="1">range value</asp:ListItem>
                <asp:ListItem Value="2">fuzzy value</asp:ListItem>
            </asp:RadioButtonList>
        </td>        
        <td width="50%">
            <asp:TextBox ID="txtSingleP" runat="server" Visible="False"></asp:TextBox>
            <br />
            <asp:Label ID="lblfrom2" runat="server" Text="from:" Visible="False"></asp:Label>
&nbsp;<asp:TextBox ID="txtRangeFromP" runat="server" Visible="False"></asp:TextBox>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Label ID="lblto2" runat="server" Text="to:" Visible="False"></asp:Label>
&nbsp;<asp:TextBox ID="txtRangeToP" runat="server" Visible="False"></asp:TextBox>
            <asp:RadioButtonList ID="RBFuzzyP" runat="server" Visible="False">
                <asp:ListItem>high</asp:ListItem>
                <asp:ListItem>medium</asp:ListItem>
                <asp:ListItem>low</asp:ListItem>
            </asp:RadioButtonList>
        </td>
    </tr>
    <tr>
        <td class="style3">
            <asp:CheckBox ID="chResponseTime" runat="server" Text="Response Time" 
                AutoPostBack="True" oncheckedchanged="chResponseTime_CheckedChanged" />
        </td>
        <td class="style5">
            <asp:RadioButtonList ID="RBR" runat="server" AutoPostBack="True" 
                Enabled="False" onselectedindexchanged="RBR_SelectedIndexChanged">
                <asp:ListItem Value="0">single value</asp:ListItem>
                <asp:ListItem Value="1">range value</asp:ListItem>
                <asp:ListItem Value="2">fuzzy value</asp:ListItem>
            </asp:RadioButtonList>
        </td>
        <td class="style1">
            <asp:TextBox ID="txtSingleR" runat="server" Visible="False"></asp:TextBox>
            <br />
            <asp:Label ID="lblfrom3" runat="server" Text="from:" Visible="False"></asp:Label>
&nbsp;<asp:TextBox ID="txtRangeFromR" runat="server" Visible="False"></asp:TextBox>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <asp:Label ID="lblto3" runat="server" Text="to:" Visible="False"></asp:Label>
&nbsp;<asp:TextBox ID="txtRangeToR" runat="server" Visible="False"></asp:TextBox>
            <asp:RadioButtonList ID="RBFuzzyR" runat="server" Visible="False">
                <asp:ListItem>high</asp:ListItem>
                <asp:ListItem>medium</asp:ListItem>
                <asp:ListItem>low</asp:ListItem>
            </asp:RadioButtonList>
        </td>
    </tr>
    </table>    
    </div>
    <asp:TextBox ID="txttest" runat="server" Height="119px" TextMode="MultiLine" 
        Width="172px"></asp:TextBox>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="btnXML" runat="server" onclick="btnXML_Click" 
        Text="XML producer" />
&nbsp;
    <asp:Button ID="btnXMLParse" runat="server" onclick="btnXMLParse_Click" 
        Text="XML Parser" />
&nbsp;<asp:Button ID="btnMIP" runat="server" onclick="btnMIP_Click" 
        Text="MIP producer" />
    <asp:TextBox ID="txtMIP" runat="server" Height="119px" TextMode="MultiLine" 
        Width="234px"></asp:TextBox>
    <asp:Button ID="btn_lp_solve" runat="server" onclick="btn_lp_solve_Click" 
        Text="test DLL" />
    </form>
</body>
</html>
