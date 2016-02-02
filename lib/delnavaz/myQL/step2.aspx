<%@ Page Language="C#" AutoEventWireup="true" CodeFile="step2.aspx.cs" Inherits="step2" %>


<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <br />
        <table border="1" cellpadding="0" cellspacing="0" style="border: medium solid #C0C0C0; " align="center">
            <tr>
                 <td colspan="2" align="center" bgcolor="#003366" style="font-size: small; color: #FFFFFF; font-weight: bolder;" >Step 2 - specifying QoS requirements</td>
            </tr>            
            <tr>
                <td colspan="0" rowspan="0" style="display: inline; border-collapse: collapse" >
                    <asp:ScriptManager ID="ScriptManager1" runat="server">
                    </asp:ScriptManager>
                    <br />
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Price" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chPrice" OnCheckedChanged="chPrice_CheckedChanged" 
                        Visible="False">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="PricePanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GP" AutoPostBack="True" Text="Range value" ID="RBPRange" Visible="False" OnCheckedChanged="RBPRange_CheckedChanged">
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Dotted" Width="98%" ID="PRangePrice" Visible="False">
                            &nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="txtRangeFromP" Visible="False"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList14">
                                <asp:ListItem>---</asp:ListItem>
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;
                            <asp:Label runat="server" Text="price" ID="lbltoP0"></asp:Label>
                            &nbsp; &nbsp;<asp:DropDownList runat="server" ID="DropDownList11">
                                <asp:ListItem>---</asp:ListItem>
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="txtRangeToP" Visible="False"></asp:TextBox>
                        </asp:Panel>
                        <br />
                        <asp:Label runat="server" Text="unit" ID="Label8"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="ddlP">
                            <asp:ListItem>US dollar</asp:ListItem>
                            <asp:ListItem>British pound</asp:ListItem>
                            <asp:ListItem>CA dollar</asp:ListItem>
                            <asp:ListItem>Euro</asp:ListItem>
                            <asp:ListItem>Yen</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GP" AutoPostBack="True" 
                            Text="Fuzzy value" Width="86px" ID="RBPFuzzy" Visible="False" 
                            OnCheckedChanged="RBPFuzzy_CheckedChanged" Height="16px">
                        </asp:RadioButton>
                        
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<asp:HyperLink 
                            ID="HyperLink1" runat="server" NavigateUrl="step3.aspx?attr=price" 
                            Target="_blank">Price Browse</asp:HyperLink>
                        <asp:RadioButtonList 
                            runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" 
                            BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" 
                            ID="RBFuzzyP" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Response Time" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chResponseTime" 
                        OnCheckedChanged="chResponseTime_CheckedChanged" Visible="False">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="RTPanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GART" AutoPostBack="True" Text="Range value" ID="RBRTRange" Visible="False" OnCheckedChanged="RBRTRange_CheckedChanged">
                        </asp:RadioButton>
                        <asp:Panel runat="server" Wrap="False" BorderWidth="1px" BorderStyle="Groove" Width="98%" ID="PRangeRT" Visible="False">
                            <asp:TextBox runat="server" Width="75px" ID="txtRangeFromRT" Visible="False"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList15">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                                <asp:ListItem>---</asp:ListItem>
                            </asp:DropDownList>
                            <asp:Label runat="server" Text="responseTime" ID="lbltoRT"></asp:Label>
                            <asp:DropDownList runat="server" ID="DropDownList9">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                                <asp:ListItem>---</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="txtRangeToRT" Visible="False"></asp:TextBox>
                            &nbsp;</asp:Panel>
                        <br />
                        <asp:Label runat="server" Text="unit" ID="Label9"></asp:Label>
                        &nbsp;
                        <asp:DropDownList runat="server" Font-Size="Small" ID="ddlRT">
                            <asp:ListItem>second</asp:ListItem>
                            <asp:ListItem>millisecond</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GART" AutoPostBack="True" 
                            Text="Fuzzy value" Width="100px" ID="RBRTFuzzy" Visible="False" 
                            OnCheckedChanged="RBRTFuzzy_CheckedChanged" Height="16px">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink2" runat="server" 
                            NavigateUrl="step3.aspx?attr=responsetime" Target="_blank">response time 
                        Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" ID="RBFuzzyRT" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Reliability" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chReliability" 
                        OnCheckedChanged="chReliability_CheckedChanged" Visible="False">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="RPanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" Text="Range value" ID="RBRRange" Visible="False" OnCheckedChanged="RBRRange_CheckedChanged">
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Groove" Width="98%" ID="PRangeR" Visible="False">
                            &nbsp;&nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="txtRangeFromR" Visible="False"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList16">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;<asp:Label runat="server" Text="reliability" ID="lbltoR"></asp:Label>
                            &nbsp;<asp:DropDownList runat="server" ID="DropDownList13">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="txtRangeToR" Visible="False"></asp:TextBox>
                        </asp:Panel>
                        &nbsp;&nbsp;<br />
                        <asp:Label runat="server" Text="unit" ID="Label10"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="ddlR">
                            <asp:ListItem>%</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Fuzzy value" Width="117px" ID="RBRFuzzy" Visible="False" 
                            OnCheckedChanged="RBRFuzzy_CheckedChanged" Height="19px">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink3" runat="server" 
                            NavigateUrl="step3.aspx?attr=reliability" Target="_blank">reliability Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" ID="RBFuzzyR" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Authentication" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chAuthentication" 
                        OnCheckedChanged="chAuthentication_CheckedChanged" Visible="False">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="AuthPanel" Visible="False">
                        <asp:RadioButton runat="server" AutoPostBack="True" Text="Single value" 
                            ID="RBAsingle" OnCheckedChanged="RBRRange_CheckedChanged">
                        </asp:RadioButton>
                        &nbsp;
                        <asp:DropDownList runat="server" ID="ddlSingleAuth">
                            <asp:ListItem>yes</asp:ListItem>
                            <asp:ListItem>no</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        &nbsp;&nbsp;&nbsp; &nbsp;
                    </asp:Panel>
                </td>
            </tr>
            <tr>
                <td style="display: inline; border-collapse: collapse" >
                    <br />
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Availability" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chAvailability" 
                        Visible="False" oncheckedchanged="chAvailability_CheckedChanged">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="AvailabilityPanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GRAv" AutoPostBack="True" 
                            Text="Range value" ID="RBRangeA" Visible="False" 
                            oncheckedchanged="RBAvailabilityRange_CheckedChanged" >
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Groove" Width="98%" 
                            ID="PRangeAvailability" Visible="False">
                            &nbsp;&nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="txtRangeFromAv"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList1">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;<asp:Label runat="server" Text="availability" ID="Label1"></asp:Label>
                            &nbsp;<asp:DropDownList runat="server" ID="DropDownList2">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="txtRangeToAv"></asp:TextBox>
                        </asp:Panel>
                        &nbsp;&nbsp;<br />
                        <asp:Label runat="server" Text="unit" ID="Label2"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="ddlAv">
                            <asp:ListItem>%</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GRAv" AutoPostBack="True" 
                            Text="Fuzzy value" Width="117px" ID="RBFuzzyA" Visible="False" 
                            Height="19px" oncheckedchanged="RBAvailabilityFuzzy_CheckedChanged">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink4" runat="server" 
                            NavigateUrl="step3.aspx?attr=reliability" Target="_blank">Availability Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" 
                            BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" 
                            ID="RBFuzzyAvailability" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Throughput" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chThroughput" 
                        Visible="False" oncheckedchanged="chThroughput_CheckedChanged">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" 
                        BorderStyle="Groove" Font-Size="Small" Width="370px" ID="ThroughputPanel" 
                        Visible="False">
                        <asp:RadioButton runat="server" GroupName="GRTh" AutoPostBack="True" 
                            Text="Range value" ID="RBThroughputRange" Visible="False" 
                            oncheckedchanged="RBThroughputRange_CheckedChanged" >
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Groove" Width="98%" 
                            ID="PRangeThroughput" Visible="False">
                            &nbsp;&nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="txtRangeFromTh"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList4">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;<asp:Label runat="server" Text="throughput" ID="Label3"></asp:Label>
                            &nbsp;<asp:DropDownList runat="server" ID="DropDownList5">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="txtRangeToTh"></asp:TextBox>
                        </asp:Panel>
                        &nbsp;&nbsp;<br />
                        <asp:Label runat="server" Text="unit" ID="Label4"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="ddlTh">
                            <asp:ListItem>%</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GRTh" AutoPostBack="True" 
                            Text="Fuzzy value" Width="117px" ID="RBThrouhputFuzzy" Visible="False" 
                            Height="19px" oncheckedchanged="RBThrouhputFuzzy_CheckedChanged">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink5" runat="server" 
                            NavigateUrl="step3.aspx?attr=reliability" Target="_blank">Throughput Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" 
                            BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" 
                            ID="PFuzzyThroughput" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Successability" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chSuccessability" 
                        Visible="False">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="SuccessabilityPanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Range value" ID="RBSucRange" Visible="False" >
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Groove" Width="98%" ID="Panel4" Visible="False">
                            &nbsp;&nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="TextBox5" Visible="False"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList7">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;<asp:Label runat="server" Text="successability" ID="Label5" Visible="False"></asp:Label>
                            &nbsp;<asp:DropDownList runat="server" ID="DropDownList8">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="TextBox6" Visible="False"></asp:TextBox>
                        </asp:Panel>
                        &nbsp;&nbsp;<br />
                        <asp:Label runat="server" Text="unit" ID="Label6"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="DropDownList10">
                            <asp:ListItem>%</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Fuzzy value" Width="117px" ID="RBSucFuzzy" Visible="False" 
                            Height="19px">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink6" runat="server" 
                            NavigateUrl="step3.aspx?attr=reliability" Target="_blank">Throughput Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" ID="RadioButtonList3" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Compliance" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chCompliance" 
                        Visible="False">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="CompliancePanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Range value" ID="RBComplianceRange" Visible="False" >
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Groove" Width="98%" ID="Panel5" Visible="False">
                            &nbsp;&nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="TextBox7" Visible="False"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList12">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;<asp:Label runat="server" Text="compliance" ID="Label7" Visible="False"></asp:Label>
                            &nbsp;<asp:DropDownList runat="server" ID="DropDownList17">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="TextBox8" Visible="False"></asp:TextBox>
                        </asp:Panel>
                        &nbsp;&nbsp;<br />
                        <asp:Label runat="server" Text="unit" ID="Label11"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="DropDownList18">
                            <asp:ListItem>%</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Fuzzy value" Width="117px" ID="RBComplianceFuzzy" Visible="False" 
                            Height="19px">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink7" runat="server" 
                            NavigateUrl="step3.aspx?attr=reliability" Target="_blank">Compliance Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" ID="RadioButtonList4" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Best Practices" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chBestPractices" 
                        Visible="False" oncheckedchanged="chBestPractices_CheckedChanged">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="BPPanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Range value" ID="RBBPRange" Visible="False" 
                            oncheckedchanged="RBBPRange_CheckedChanged" >
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Groove" Width="98%" 
                            ID="PRangeBP" Visible="False">
                            &nbsp;&nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="TextBox9"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList19">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;<asp:Label runat="server" Text="best practices" ID="Label12"></asp:Label>
                            &nbsp;<asp:DropDownList runat="server" ID="DropDownList20">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="TextBox10"></asp:TextBox>
                        </asp:Panel>
                        &nbsp;&nbsp;<br />
                        <asp:Label runat="server" Text="unit" ID="Label13"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="DropDownList21">
                            <asp:ListItem>%</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Fuzzy value" Width="117px" ID="RBBPFuzzy" Visible="False" 
                            Height="19px" oncheckedchanged="RBBPFuzzy_CheckedChanged">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink8" runat="server" 
                            NavigateUrl="step3.aspx?attr=reliability" Target="_blank">Best Practices Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" 
                            BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" 
                            ID="RBFuzzyBP" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Latency" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chLatency" 
                        Visible="False" oncheckedchanged="chLatency_CheckedChanged">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="LatencyPanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GRL" AutoPostBack="True" 
                            Text="Range value" ID="RBLatencyRange" 
                            oncheckedchanged="RBLatencyRange_CheckedChanged" >
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Groove" Width="98%" 
                            ID="PRangeL" Visible="False">
                            &nbsp;&nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="txtRangeFromL"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList22">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;<asp:Label runat="server" Text="best practices" ID="Label14"></asp:Label>
                            &nbsp;<asp:DropDownList runat="server" ID="DropDownList23">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="txtRangeToL"></asp:TextBox>
                        </asp:Panel>
                        &nbsp;&nbsp;<br />
                        <asp:Label runat="server" Text="unit" ID="Label15"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="ddlLt">
                            <asp:ListItem>%</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GRL" AutoPostBack="True" 
                            Text="Fuzzy value" Width="117px" ID="RBLatencyFuzzy" 
                            Height="19px" oncheckedchanged="RBLatencyFuzzy_CheckedChanged">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink9" runat="server" 
                            NavigateUrl="step3.aspx?attr=reliability" Target="_blank">Latency Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" 
                            BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" 
                            ID="RBFuzzyL" Visible="False">
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
                    <asp:CheckBox runat="server" AutoPostBack="True" Text="Documentation" 
                        BackColor="Silver" BorderColor="Black" BorderWidth="1px" BorderStyle="Dotted" 
                        Font-Size="Small" ID="chDocumentation" 
                        Visible="False" oncheckedchanged="chDocumentation_CheckedChanged">
                    </asp:CheckBox>
                    <asp:Panel runat="server" BackColor="#CCCCCC" BorderWidth="1px" BorderStyle="Groove" Font-Size="Small" Width="370px" ID="DocumentationPanel" Visible="False">
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Range value" ID="RBDocRange" Visible="False" 
                            oncheckedchanged="RBDocRange_CheckedChanged" >
                        </asp:RadioButton>
                        <asp:Panel runat="server" BorderWidth="1px" BorderStyle="Groove" Width="98%" 
                            ID="PRangeDoc" Visible="False">
                            &nbsp;&nbsp;&nbsp;<asp:TextBox runat="server" Width="75px" ID="TextBox13"></asp:TextBox>
                            <asp:DropDownList runat="server" ID="DropDownList25">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            &nbsp;<asp:Label runat="server" Text="documentation" ID="Label16"></asp:Label>
                            &nbsp;<asp:DropDownList runat="server" ID="DropDownList26">
                                <asp:ListItem>&lt;</asp:ListItem>
                                <asp:ListItem>&lt;=</asp:ListItem>
                                <asp:ListItem>=</asp:ListItem>
                            </asp:DropDownList>
                            <asp:TextBox runat="server" Width="75px" ID="TextBox14"></asp:TextBox>
                        </asp:Panel>
                        &nbsp;&nbsp;<br />
                        <asp:Label runat="server" Text="unit" ID="Label17"></asp:Label>
                        &nbsp;<asp:DropDownList runat="server" ID="DropDownList27">
                            <asp:ListItem>%</asp:ListItem>
                        </asp:DropDownList>
                        <br />
                        <br />
                        <asp:RadioButton runat="server" GroupName="GR" AutoPostBack="True" 
                            Text="Fuzzy value" Width="117px" ID="RBDocFuzzy" Visible="False" 
                            Height="19px" oncheckedchanged="RBDocFuzzy_CheckedChanged">
                        </asp:RadioButton>
                        &nbsp;&nbsp;&nbsp;<asp:HyperLink ID="HyperLink10" runat="server" 
                            NavigateUrl="step3.aspx?attr=reliability" Target="_blank">Documentation Browse</asp:HyperLink>
&nbsp;<asp:RadioButtonList runat="server" CellPadding="2" CellSpacing="5" RepeatDirection="Horizontal" 
                            BorderWidth="1px" BorderStyle="Dotted" Height="18px" Width="342px" 
                            ID="RBFuzzyDoc" Visible="False">
                            <asp:ListItem>best available</asp:ListItem>
                            <asp:ListItem>good</asp:ListItem>
                            <asp:ListItem>medium</asp:ListItem>
                            <asp:ListItem>poor</asp:ListItem>
                        </asp:RadioButtonList>
                    </asp:Panel>
                </td>

            </tr>                      
            <tr>
                <td colspan="0" rowspan="0" style="display: inline; border-collapse: collapse" >
                    <br />
                </td>
            </tr>
            <tr>
                <td bgcolor="#003366" >
                    <asp:Button ID="btnCancel" runat="server" Text="Reset" CausesValidation="false" 
                        onclick="btnCancel_Click" />
                     <asp:Button ID="btnPrevious" runat="server" Text="Previous" 
                        CausesValidation="false" nclick="btnNext_Click" 
                        onclick="btnPrevious_Click" />
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <asp:Button ID="btnBrowse" runat="server" Text="Browse" 
                        CausesValidation="false" nclick="btnNext_Click" onclick="btnBrowse_Click" 
                        Visible="False" />
                     <asp:Button ID="btnNext" runat="server" Text="Next" CausesValidation="false" 
                        nclick="btnNext_Click" onclick="btnNext_Click" />
               </td>
            </tr>            
        </table>
    </div>
    </form>
</body>
</html>

