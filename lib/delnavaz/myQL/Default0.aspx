<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default0.aspx.cs" Inherits="Default0" %>

<%@ Register src="infoDel.ascx" tagname="infoDel" tagprefix="uc1" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>

    </head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:Wizard ID="Wizard1" runat="server" ActiveStepIndex="3" BackColor="#EFF3FB" 
            BorderColor="#B5C7DE" BorderWidth="1px" FinishCompleteButtonText="Submit" 
            FinishDestinationPageUrl="~/result.aspx" Font-Names="Verdana" Font-Size="Small" 
            HeaderText="XQQL Framework" Height="345px" 
            onfinishbuttonclick="Wizard1_FinishButtonClick" 
            CancelDestinationPageUrl="~/Default0.aspx" CancelButtonText="Reset" 
            DisplayCancelButton="True" 
            oncancelbuttonclick="Wizard1_CancelButtonClick" >
            <StepStyle Font-Size="Medium" ForeColor="#333333" Width="800px"/>
            <HeaderTemplate>
                <b><%=Wizard1.ActiveStep.Title %></b><br /><br />
            </HeaderTemplate>

            <NavigationStyle HorizontalAlign="Center" />

            <WizardSteps>
                <asp:WizardStep runat="server" title="Step 1 - selecting QoS attributes ">
                    <table align="center" border="0" style="border: medium solid #C0C0C0" 
                        width="50%" >
                        <tr>
                            <td class="style1">
                                <asp:DropDownList ID="ddlQoS" runat="server">
                                    <asp:ListItem>-----</asp:ListItem>
                                    <asp:ListItem>Availability</asp:ListItem>
                                    <asp:ListItem>Response Time</asp:ListItem>
                                    <asp:ListItem>Price</asp:ListItem>
                                    <asp:ListItem>Reliability</asp:ListItem>
                                    <asp:ListItem>authentication</asp:ListItem>
                                    <asp:ListItem>Stability</asp:ListItem>
                                </asp:DropDownList>
                                &nbsp;
                                <asp:Button ID="btnAdd" runat="server" Text="Add" Width="43px" Font-Size="Small" OnClick="btnAdd_Click" />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td >
                                &nbsp;</td>
                            <td align="left">
                                <asp:Label ID="Label6" runat="server" Font-Size="Small" Text="price"></asp:Label>
                            </td>
                            <td align="left">
                                <asp:ImageButton ID="btnInfo" runat="server" 
                                    ImageUrl="~/images/info-icon-preview.jpg" 
                                    Width="20px" OnClick="btnInfo_Click1" Height="20px" />
                                <asp:ImageButton ID="btnDel" runat="server" 
                                    ImageUrl="~/images/psd-delete-icon.jpg" Width="20px" Height="20px" />                            
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <asp:CheckBoxList ID="chbL1" runat="server" Font-Size="Small">
                                </asp:CheckBoxList>
                            </td>
                            <td align="left">
                                <asp:Label ID="Label1" runat="server" Font-Size="Small" Text="reponse time"></asp:Label>
                           </td>
                           <td align="left">
                                <asp:ImageButton ID="ImageButton1" runat="server" 
                                    ImageUrl="~/images/info-icon-preview.jpg" 
                                    Width="20px" />
                                <asp:ImageButton ID="ImageButton2" runat="server" 
                                    ImageUrl="~/images/psd-delete-icon.jpg"  Width="22px" Height="20px" />                                                       
                           </td>
                        </tr>
                        <tr>
                            <td >
                                &nbsp;</td>
                            <td align="left">
                                <asp:Label ID="Label2" runat="server" Font-Size="Small" Text="reliability"></asp:Label>
                            </td>
                            <td align="left">  
                                <asp:ImageButton ID="ImageButton3" runat="server" 
                                    ImageUrl="~/images/info-icon-preview.jpg" 
                                    Width="20px" Height="20px" />
                                <asp:ImageButton ID="ImageButton4" runat="server" 
                                    ImageUrl="~/images/psd-delete-icon.jpg" Width="20px" Height="20px" />                            
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <asp:PlaceHolder ID="PlaceHolder1" runat="server"></asp:PlaceHolder>
                            </td>
                            <td align="left">
                                <asp:Label ID="Label7" runat="server" Font-Size="Small" Text="authentication"></asp:Label>
                            </td>
                            <td align="left">
                                <asp:ImageButton ID="ImageButton5" runat="server" 
                                    ImageUrl="~/images/info-icon-preview.jpg"  
                                    Width="20px" Height="20px" />
                                <asp:ImageButton ID="ImageButton6" runat="server" 
                                    ImageUrl="~/images/psd-delete-icon.jpg" Width="20px" Height="20px" />                            
                           </td>
                        </tr>
                        <tr>
                            <td >
                                &nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;</td>
                            <td>
                                &nbsp;</td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;</td>
                            <td>
                                &nbsp;</td>
                        </tr>
                        <tr>
                            <td class="style1">
                                </td>
                            <td class="style3">
                                </td>
                        </tr>
                        <tr>
                            <td class="style1">
                                &nbsp;</td>
                            <td>
                                &nbsp;</td>
                        </tr>
                        <tr>
                            <td class="style1">
                                &nbsp;</td>
                            <td>
                                &nbsp;</td>
                        </tr>
                    </table>
                </asp:WizardStep>
                <asp:WizardStep runat="server" title="Step 2 - specifying QoS requirements">
                    <table align="center" border="0" >
                        <tr>
                            <td align="justify" colspan="0" rowspan="0" valign="top">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<table border="1" cellpadding="0" cellspacing="0" 
                                    
                                    style="border: thick groove #FFFFFF; table-layout: auto; height: 568px; width: 393px;">
                                    <tr>
                                        <td colspan="0" rowspan="0" 
                                            style="display: inline; border-collapse: collapse" >
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <br />
                                            <asp:CheckBox ID="chPrice" runat="server" AutoPostBack="True" 
                                                BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                                                Font-Size="Small" OnCheckedChanged="chPrice_CheckedChanged" Text="Price" />
                                            <asp:Panel ID="PricePanel" runat="server" BackColor="#CCCCCC" 
                                                BorderStyle="Groove" BorderWidth="1px" Font-Size="Small" Visible="False" 
                                                Width="370px">
                                                <asp:RadioButton ID="RBPRange" runat="server" AutoPostBack="True" 
                                                    GroupName="GP" OnCheckedChanged="RBPRange_CheckedChanged" Text="Range value" 
                                                    Visible="False" />
                                                <asp:Panel ID="PRangePrice" runat="server" BorderStyle="Dotted" 
                                                    BorderWidth="1px" Visible="False" Width="98%">
                                                    &nbsp;&nbsp;<asp:TextBox ID="txtRangeFromP" runat="server" Visible="False" 
                                                        Width="75px"></asp:TextBox>
                                                    <asp:DropDownList ID="DropDownList14" runat="server">
                                                        <asp:ListItem>---</asp:ListItem>
                                                        <asp:ListItem>&lt;</asp:ListItem>
                                                        <asp:ListItem>&lt;=</asp:ListItem>
                                                        <asp:ListItem>=</asp:ListItem>
                                                    </asp:DropDownList>
                                                    &nbsp;
                                                    <asp:Label ID="lbltoP0" runat="server" Text="price"></asp:Label>
                                                    &nbsp; &nbsp;<asp:DropDownList ID="DropDownList11" runat="server">
                                                        <asp:ListItem>---</asp:ListItem>                                                    
                                                        <asp:ListItem>&lt;</asp:ListItem>
                                                        <asp:ListItem>&lt;=</asp:ListItem>
                                                        <asp:ListItem>=</asp:ListItem>
                                                    </asp:DropDownList>
                                                    <asp:TextBox ID="txtRangeToP" runat="server" Visible="False" Width="75px"></asp:TextBox>
                                                </asp:Panel>
                                                <br />
                                                <asp:Label ID="Label8" runat="server" Text="unit"></asp:Label>
&nbsp;<asp:DropDownList ID="DropDownList5" runat="server">
                                                    <asp:ListItem>US dollar</asp:ListItem>
                                                    <asp:ListItem>British pound</asp:ListItem>
                                                    <asp:ListItem>CA dollar</asp:ListItem>
                                                    <asp:ListItem>Euro</asp:ListItem>
                                                    <asp:ListItem>Yen</asp:ListItem>
                                                </asp:DropDownList>
                                                <br />
                                                <br />
                                                <asp:RadioButton ID="RBPFuzzy" runat="server" AutoPostBack="True" 
                                                    GroupName="GP" OnCheckedChanged="RBPFuzzy_CheckedChanged" Text="Fuzzy value" 
                                                    Visible="False" Width="200px" />
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<asp:RadioButtonList ID="RBFuzzyRT0" runat="server" 
                                                    BorderStyle="Dotted" BorderWidth="1px" CellPadding="2" CellSpacing="5" 
                                                    Height="18px" RepeatDirection="Horizontal" Visible="False" Width="342px">
                                                    <asp:ListItem>best available</asp:ListItem>
                                                    <asp:ListItem>good</asp:ListItem>
                                                    <asp:ListItem>medium</asp:ListItem>
                                                    <asp:ListItem>poor</asp:ListItem>
                                                </asp:RadioButtonList>
                                                </asp:Panel>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="display: inline; border-collapse: collapse" class="style2">
                                            <br />
                                            <asp:CheckBox ID="chResponseTime" runat="server" AutoPostBack="True" 
                                                BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                                                Font-Size="Small" OnCheckedChanged="chResponseTime_CheckedChanged" 
                                                Text="Response Time" />
                                            <asp:Panel ID="RTPanel" runat="server" BackColor="#CCCCCC" BorderStyle="Groove" 
                                                BorderWidth="1px" Font-Size="Small" Visible="False" Width="370px">
                                                <asp:RadioButton ID="RBRTRange" runat="server" AutoPostBack="True" 
                                                    GroupName="GART" OnCheckedChanged="RBRTRange_CheckedChanged" Text="Range value" 
                                                    Visible="False" />
                                                <asp:Panel ID="PRangeRT" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                                                    Visible="False" Width="98%" Wrap="False">
                                                    <asp:TextBox ID="txtRangeFromRT" runat="server" Visible="False" Width="75px"></asp:TextBox>
                                                    <asp:DropDownList ID="DropDownList15" runat="server">
                                                        <asp:ListItem>&lt;</asp:ListItem>
                                                        <asp:ListItem>&lt;=</asp:ListItem>
                                                        <asp:ListItem>=</asp:ListItem>
                                                        <asp:ListItem>---</asp:ListItem>
                                                    </asp:DropDownList>
                                                    <asp:Label ID="lbltoRT" runat="server" Text="responseTime"></asp:Label>
                                                    <asp:DropDownList ID="DropDownList9" runat="server">
                                                        <asp:ListItem>&lt;</asp:ListItem>
                                                        <asp:ListItem>&lt;=</asp:ListItem>
                                                        <asp:ListItem>=</asp:ListItem>
                                                        <asp:ListItem>---</asp:ListItem>
                                                    </asp:DropDownList>
                                                    <asp:TextBox ID="txtRangeToRT" runat="server" Visible="False" Width="75px"></asp:TextBox>
                                                    &nbsp;</asp:Panel>
                                                <br />
                                                <asp:Label ID="Label9" runat="server" Text="unit"></asp:Label>
&nbsp;
                                                <asp:DropDownList ID="DropDownList6" runat="server" Font-Size="Small">
                                                    <asp:ListItem>second</asp:ListItem>
                                                    <asp:ListItem>millisecond</asp:ListItem>
                                                </asp:DropDownList>
                                                <br />
                                                <br />
                                                <asp:RadioButton ID="RBRTFuzzy" runat="server" AutoPostBack="True" 
                                                    GroupName="GART" OnCheckedChanged="RBRTFuzzy_CheckedChanged" Text="Fuzzy value" 
                                                    Visible="False" Width="200px" />
                                                &nbsp;&nbsp;&nbsp;
                                                <asp:RadioButtonList ID="RBFuzzyRT" runat="server" BorderStyle="Dotted" 
                                                    BorderWidth="1px" RepeatDirection="Horizontal" 
                                                    Visible="False" Width="342px" CellPadding="2" CellSpacing="5" 
                                                    Height="18px">
                                                    <asp:ListItem>best available</asp:ListItem>
                                                    <asp:ListItem>good</asp:ListItem>
                                                    <asp:ListItem>medium</asp:ListItem>
                                                    <asp:ListItem>poor</asp:ListItem>
                                                </asp:RadioButtonList>
                                            </asp:Panel>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="display: inline; border-collapse: collapse" >
                                            <br />
                                            <asp:CheckBox ID="chReliability" runat="server" AutoPostBack="True" 
                                                BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                                                Font-Size="Small" OnCheckedChanged="chReliability_CheckedChanged" 
                                                Text="Reliability" />
                                            <asp:Panel ID="RPanel" runat="server" BackColor="#CCCCCC" BorderStyle="Groove" 
                                                BorderWidth="1px" Font-Size="Small" Visible="False" Width="370px">
                                                <asp:RadioButton ID="RBRRange" runat="server" AutoPostBack="True" 
                                                    GroupName="GR" OnCheckedChanged="RBRRange_CheckedChanged" Text="Range value" 
                                                    Visible="False" />
                                                <asp:Panel ID="PRangeR" runat="server" BorderStyle="Groove" BorderWidth="1px" 
                                                    Visible="False" Width="98%">
                                                    &nbsp;&nbsp;&nbsp;<asp:TextBox ID="txtRangeFromR" runat="server" Visible="False" Width="75px"></asp:TextBox>
                                                    <asp:DropDownList ID="DropDownList16" runat="server">
                                                        <asp:ListItem>&lt;</asp:ListItem>
                                                        <asp:ListItem>&lt;=</asp:ListItem>
                                                        <asp:ListItem>=</asp:ListItem>
                                                    </asp:DropDownList>
                                                    &nbsp;<asp:Label ID="lbltoR" runat="server" Text="reliability" Visible="False"></asp:Label>
                                                    &nbsp;<asp:DropDownList ID="DropDownList13" runat="server">
                                                        <asp:ListItem>&lt;</asp:ListItem>
                                                        <asp:ListItem>&lt;=</asp:ListItem>
                                                        <asp:ListItem>=</asp:ListItem>
                                                    </asp:DropDownList>
                                                    <asp:TextBox ID="txtRangeToR" runat="server" Visible="False" Width="75px"></asp:TextBox>
                                                </asp:Panel>
                                                &nbsp;&nbsp;<br />
                                                <asp:Label ID="Label10" runat="server" Text="unit" Visible="False"></asp:Label>
                                                &nbsp;<asp:DropDownList ID="DropDownList7" runat="server" Visible="False">
                                                    <asp:ListItem>percent</asp:ListItem>
                                                </asp:DropDownList>
                                                <br />
                                                <br />
                                                <asp:RadioButton ID="RBRFuzzy" runat="server" AutoPostBack="True" 
                                                    GroupName="GR" OnCheckedChanged="RBRFuzzy_CheckedChanged" Text="Fuzzy value" 
                                                    Visible="False" Width="200px" />
                                                &nbsp;&nbsp;&nbsp;
                                                <asp:RadioButtonList ID="RBFuzzyR" runat="server" BorderStyle="Dotted" 
                                                    BorderWidth="1px" RepeatDirection="Horizontal" 
                                                    Visible="False" Width="342px" CellPadding="2" CellSpacing="5" 
                                                    Height="18px">
                                                    <asp:ListItem>best available</asp:ListItem>
                                                    <asp:ListItem>good</asp:ListItem>
                                                    <asp:ListItem>medium</asp:ListItem>
                                                    <asp:ListItem>poor</asp:ListItem>
                                                </asp:RadioButtonList>
                                            </asp:Panel>
                                        </td>
                                    </tr>
<tr>
                                        <td style="display: inline; border-collapse: collapse" >
                                            <br />
                                            <asp:CheckBox ID="chAuthentication" runat="server" AutoPostBack="True" 
                                                BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                                                Font-Size="Small" OnCheckedChanged="chAuthentication_CheckedChanged" 
                                                Text="Authentication" />
                                            <asp:Panel ID="AuthPanel" runat="server" BackColor="#CCCCCC" BorderStyle="Groove" 
                                                BorderWidth="1px" Font-Size="Small" Visible="False" Width="370px">
                                                <asp:RadioButton ID="RBTsingle" runat="server" AutoPostBack="True" OnCheckedChanged="RBRRange_CheckedChanged" 
                                                    Text="Single value" />
                                                &nbsp;
                                                <asp:DropDownList ID="ddlSingleAuth" runat="server">
                                                    <asp:ListItem>yes</asp:ListItem>
                                                    <asp:ListItem>no</asp:ListItem>
                                                </asp:DropDownList>
                                                <br />
                                                &nbsp;&nbsp;&nbsp; &nbsp;</asp:Panel>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td colspan="0" rowspan="0" 
                                            style="display: inline; border-collapse: collapse" >
                                            <br />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                                <asp:ImageButton ID="ImageButton9" runat="server" 
                                    ImageUrl="~/images/browse3.png" OnClick="ImageButton9_Click" ToolTip="Browse" 
                                    Visible="False" Width="22px" />
                                <asp:XmlDataSource ID="XmlDataSource1" runat="server" 
                                    DataFile="~/App_Data/Clusters.xml"></asp:XmlDataSource>
                                <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
                                    CellPadding="4" DataSourceID="XmlDataSource1" Font-Size="Small" 
                                    ForeColor="#333333" Visible="False">
                                    <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                                    <Columns>
                                        <asp:BoundField DataField="name" HeaderText="name" SortExpression="name" />
                                        <asp:BoundField DataField="size" HeaderText="size" SortExpression="size" />
                                        <asp:BoundField DataField="reliabilityRange" HeaderText="reliabilityRange" 
                                            SortExpression="reliabilityRange" />
                                        <asp:BoundField DataField="reposnseTimeRange" HeaderText="reposnseTimeRange" 
                                            SortExpression="reposnseTimeRange" />
                                        <asp:BoundField DataField="priceRange" HeaderText="priceRange" 
                                            SortExpression="priceRange" />
                                    </Columns>
                                    <EditRowStyle BackColor="#999999" />
                                    <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                                    <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                                    <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                                    <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
                                    <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                                </asp:GridView>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                &nbsp;</td>
                            <td>
                                &nbsp;</td>
                        </tr>
                    </table>
                </asp:WizardStep>
                <asp:WizardStep runat="server" Title="Step3 - Preferences and Relaxations">
                    <table align="center" border="1" style="border: medium solid #C0C0C0; " 
                        width="40%">
                        <tr>
                            <td style="display: inline; border-collapse: collapse" 
                                valign="top" class="style1">
                                <asp:CheckBox ID="chPreferences" runat="server" AutoPostBack="True" 
                                    BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                                    Font-Size="Small" OnCheckedChanged="chPreferences_CheckedChanged" 
                                    Text="Order of Preferences" />
                                <asp:Panel ID="Panel17" runat="server" BackColor="#CCCCCC" BorderStyle="Dotted" 
                                    BorderWidth="1px" Font-Size="Small">
                                    <table align="center" border="0" width="70%">
                                        <tr>
                                            <td bgcolor="#CCCCCC">
                                                <asp:Panel ID="PanelPref" runat="server">
                                                    <br />
                                                    <br />
                                                </asp:Panel>
                                            </td>
                                            <td align="left">
                                                <asp:Label ID="lblPriority" runat="server" Text="Priority"></asp:Label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td >
                                                <asp:CheckBox ID="chPricePref" runat="server" AutoPostBack="True" 
                                                    Text="Price" />
                                            </td>
                                            <td >
                                                <asp:TextBox ID="TextBox21" runat="server" Width="75px"></asp:TextBox>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <asp:CheckBox ID="chResponseTimePref" runat="server" AutoPostBack="True" 
                                                    Text="Response Time" />
                                            </td>
                                            <td>
                                                <asp:TextBox ID="TextBox7" runat="server" Width="75px"></asp:TextBox>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <asp:CheckBox ID="chReliabilityPref" runat="server" AutoPostBack="True" 
                                                    Text="reliability" />
                                            </td>
                                            <td>
                                                <asp:TextBox ID="TextBox8" runat="server" Width="75px"></asp:TextBox>
                                            </td>
                                        </tr>
                                        <tr >
                                            <td>
                                                <asp:CheckBox ID="chAuthPre" runat="server" AutoPostBack="True" 
                                                    Text="Authentication" />
                                            </td>
                                            <td>
                                                <asp:TextBox ID="TextBox22" runat="server" Width="75px"></asp:TextBox>
                                            </td>
                                        </tr>
                                    </table>
                                </asp:Panel>
                            </td>
                        </tr>
                        <tr>
                            <td style="display: inline; border-collapse: collapse" 
                                valign="top" >
                                <asp:CheckBox ID="chRelaxationSet" runat="server" AutoPostBack="True" 
                                    BackColor="Silver" BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" 
                                    Font-Size="Small" Text="Order of Relaxation " />
                                <asp:Panel ID="Panel18" runat="server" BackColor="#CCCCCC" BorderStyle="Dotted" 
                                    BorderWidth="1px" Font-Size="Small">
                                    <table align="center" border="0" cellpadding="0" cellspacing="0" 
                                         width="70%">
                                        <tr>
                                            <td bgcolor="#CCCCCC">
                                                <asp:Panel ID="PanelPref0" runat="server">
                                                    <br />
                                                    <br />
                                                </asp:Panel>
                                            </td>
                                            <td align="left">
                                                <asp:Label ID="lblPriority0" runat="server" Text="Priority"></asp:Label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td >
                                                <asp:CheckBox ID="chPrice1" runat="server" AutoPostBack="True" Text="Price" />
                                            </td>
                                            <td >
                                                <asp:TextBox ID="TextBox13" runat="server" Width="75px"></asp:TextBox>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <asp:CheckBox ID="chResponseTime1" runat="server" AutoPostBack="True" 
                                                    Text="Response Time" />
                                            </td>
                                            <td>
                                                <asp:TextBox ID="TextBox17" runat="server" Width="75px"></asp:TextBox>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <asp:CheckBox ID="chReliability1" runat="server" AutoPostBack="True" 
                                                    Text="reliability" />
                                            </td>
                                            <td>
                                                <asp:TextBox ID="TextBox19" runat="server" Width="75px"></asp:TextBox>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <asp:CheckBox ID="chAuth" runat="server" AutoPostBack="True" 
                                                    Text="Authentication" />
                                            </td>
                                            <td>
                                                <asp:TextBox ID="TextBox1" runat="server" Width="75px"></asp:TextBox>
                                            </td>
                                        </tr>                                        
                                    </table>
                                    <br />
                                    <br />
                                </asp:Panel>
                            </td>
                        </tr>
                    </table>
                </asp:WizardStep>
                <asp:WizardStep runat="server" Title="Step4 - defining time constraints">
                    <table align="center" border="0" >
                        <tr align="left">
                            <td align="left">
                                <asp:ScriptManager ID="ScriptManager1" runat="server">
                                </asp:ScriptManager>
                            </td>
                        </tr>
                        <tr>
                            <td align="justify" colspan="0" rowspan="0" valign="top">
                                <asp:CheckBox ID="CheckBox5" runat="server" 
                                    BorderColor="Black" BorderStyle="Dotted" BorderWidth="1px" Font-Size="Small" 
                                    Text="Time" />
                                &nbsp;<asp:ImageButton ID="ImageButton7" runat="server" 
                                    ImageUrl="~/images/info-icon-preview.jpg" Width="20px" Height="20px" />
&nbsp;<table border="1" cellpadding="0" cellspacing="0" 
                                    style="border: thick groove #FFFFFF; table-layout: auto; " width="70%">
                                    <tr>
                                        <td colspan="0" rowspan="0" 
                                            style="display: inline; border-collapse: collapse">
                                            <asp:Panel ID="Panel19" runat="server" BorderWidth="0px">
                                                <table ID="tblTime" align="center" width="470px">
                                                    <tr>
                                                        <td width="50%">
                                                            <asp:CheckBox ID="CheckBox1" runat="server" Font-Size="Small" 
                                                                Text="Start Date" />
                                                            <asp:TextBox ID="TextBox20" runat="server" Font-Size="X-Small" Width="95px">[selected_date]</asp:TextBox>
                                                            <cc1:CalendarExtender ID="TextBox20_CalendarExtender" runat="server" 
                                                                Enabled="True" Format="MM/dd/yyyy" PopupButtonID="cal1" 
                                                                TargetControlID="TextBox20">
                                                            </cc1:CalendarExtender>
                                                            <asp:ImageButton ID="cal1" runat="server" ImageUrl="~/images/cal8.jpg" 
                                                                Width="20px" />
                                                        </td>
                                                        <td width="50%">
                                                            <asp:CheckBox ID="CheckBox2" runat="server" Font-Size="Small" Text="End Date" />
                                                            <asp:TextBox ID="TextBox12" runat="server" Font-Size="X-Small" Width="95px">[selected_date]</asp:TextBox>
                                                            <cc1:CalendarExtender ID="TextBox12_CalendarExtender" runat="server" 
                                                                Enabled="True" PopupButtonID="cal2" TargetControlID="TextBox12">
                                                            </cc1:CalendarExtender>
                                                            <asp:ImageButton ID="cal2" runat="server" ImageUrl="~/images/cal8.jpg" 
                                                                Width="20px" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <br />
                                                            <asp:CheckBox ID="CheckBox3" runat="server" Font-Size="Small" 
                                                                Text="Duration of usage" />
                                                            &nbsp;
                                                            <asp:DropDownList ID="DropDownList3" runat="server" Font-Size="Small">
                                                                <asp:ListItem>1</asp:ListItem>
                                                                <asp:ListItem>2</asp:ListItem>
                                                                <asp:ListItem>3</asp:ListItem>
                                                                <asp:ListItem>4</asp:ListItem>
                                                                <asp:ListItem>5</asp:ListItem>
                                                                <asp:ListItem>6</asp:ListItem>
                                                                <asp:ListItem>7</asp:ListItem>
                                                                <asp:ListItem>8</asp:ListItem>
                                                                <asp:ListItem>9</asp:ListItem>
                                                                <asp:ListItem>10</asp:ListItem>
                                                            </asp:DropDownList>
                                                            &nbsp;&nbsp;
                                                            <asp:DropDownList ID="DropDownList4" runat="server" Font-Size="Small">
                                                                <asp:ListItem>hours</asp:ListItem>
                                                                <asp:ListItem></asp:ListItem>
                                                            </asp:DropDownList>
                                                            &nbsp;<asp:Label ID="Label5" runat="server" Font-Size="Small" Text="per invocation"></asp:Label>
                                                            &nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <br />
                                                            <asp:CheckBox ID="CheckBox4" runat="server" Font-Size="Small" 
                                                                Text="Frequency of usage" />
                                                            &nbsp;<asp:DropDownList ID="DropDownList1" runat="server" Font-Size="Small">
                                                                <asp:ListItem>1</asp:ListItem>
                                                                <asp:ListItem>2</asp:ListItem>
                                                                <asp:ListItem>3</asp:ListItem>
                                                                <asp:ListItem>4</asp:ListItem>
                                                                <asp:ListItem>5</asp:ListItem>
                                                                <asp:ListItem>6</asp:ListItem>
                                                                <asp:ListItem>7</asp:ListItem>
                                                                <asp:ListItem>8</asp:ListItem>
                                                                <asp:ListItem>9</asp:ListItem>
                                                                <asp:ListItem>10</asp:ListItem>
                                                            </asp:DropDownList>
                                                            &nbsp;<asp:Label ID="Label3" runat="server" Font-Size="Small" Text="times per"></asp:Label>
                                                            &nbsp;<asp:DropDownList ID="DropDownList2" runat="server" Font-Size="Small">
                                                                <asp:ListItem>day</asp:ListItem>
                                                                <asp:ListItem>week</asp:ListItem>
                                                                <asp:ListItem>month</asp:ListItem>
                                                                <asp:ListItem>year</asp:ListItem>
                                                            </asp:DropDownList>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </asp:Panel>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                &nbsp;</td>
                        </tr>
                    </table>
                </asp:WizardStep>
            </WizardSteps>
            <StartNavigationTemplate>
               <table cellpadding="3" cellspacing="3" border="0" width="50%" >
                     <tr align="center">
                         <td align="left" width="40%">
                             <asp:Button ID="btnCancel" runat="server" Text="Reset" CausesValidation="false" />
                         </td>
                         <td width="10%">
                             <asp:Button ID="btnNext" runat="server" Text="Next" CausesValidation="false" />
                         </td>
                     </tr>                     
                 </table>            
            </StartNavigationTemplate>
           <StepNavigationTemplate>
               <table cellpadding="3" cellspacing="3" border="0" width="40%" >
                     <tr align="center">
                         <td align="left" width="40%">
                            <asp:Button ID="btnCancel" runat="server" Text="Reset" CausesValidation="false" />
                         </td>                
                         <td align="right">
                             <!--<asp:Button ID="btnBrowse" runat="server" Text="Browse" CausesValidation="false" />-->
                             <asp:Button ID="btnPrevious" runat="server" Text="Previous" CausesValidation="false" /> 
                             <asp:Button ID="btnNext" runat="server" Text="Next" CausesValidation="false" />
                         </td>
                     </tr>                     
                 </table>
          </StepNavigationTemplate>          
          <FinishNavigationTemplate>
               <table cellpadding="3" cellspacing="3" border="0" width="70%" >
                     <tr align="center">
                         <td align="center" width="40%">
                             <asp:Button ID="btnCancel" runat="server" Text="Reset" CausesValidation="false" />
                         </td>
                         <td >
                             <asp:Button ID="btnPrevious" runat="server" Text="Previous" CausesValidation="false" />
                         
                             <asp:Button ID="btnFinish" runat="server" Text="Submit" CausesValidation="false"/>
                         </td>
                     </tr>
                 </table>          
          </FinishNavigationTemplate>
            <SideBarButtonStyle BackColor="#336699" Font-Names="Verdana" 
                ForeColor="White" Width="200px" />
            <NavigationButtonStyle BackColor="White" BorderColor="#507CD1" 
                BorderStyle="Solid" BorderWidth="1px" Font-Names="Verdana" Font-Size="0.8em" 
                ForeColor="#284E98" />
            <SideBarStyle BackColor="#336699" Font-Size="0.9em" VerticalAlign="Top" 
                HorizontalAlign="Center" />
            <HeaderStyle BackColor="#284E98" BorderColor="#EFF3FB" BorderStyle="Solid" 
                BorderWidth="2px" Font-Bold="True" Font-Size="0.9em" ForeColor="White" 
                HorizontalAlign="Center" VerticalAlign="Middle" />
        </asp:Wizard>
    </div>
    </form>
</body>
</html>
