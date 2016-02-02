<%@ Control Language="C#" AutoEventWireup="true" CodeFile="infoDel.ascx.cs" Inherits="infoDel" %>

<asp:Panel ID="Panel1" runat="server">
    <asp:Label ID="lblQoSAttr" runat="server"></asp:Label>
    <asp:ImageButton ID="btnInfo" runat="server" 
        ImageUrl="~/images/info-icon-preview.jpg" Width="22px" 
        onclick="btnInfo_Click" />
    <asp:ImageButton ID="btnDel" runat="server" 
        ImageUrl="~/images/psd-delete-icon.jpg" Width="22px" 
        onclick="btnDel_Click" />
    <asp:Label ID="lblInfo" runat="server"></asp:Label>
</asp:Panel>
