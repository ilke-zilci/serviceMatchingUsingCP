#Pricing Schema
Schema describing cloud resources and their pricing.

Examples of resources entities can be found in the service-catalog. (https://nuv.la/api/service-offer or https://129.194.184.194/api/service-info)
Also see documentation: http://ssapi.sixsq.com/#get-the-service-offer

###Note:
 * Schema still in dev and may change
 * The prototype (http//lsds.hesge.ch/pacha) is outdated and its data are not updated anymore.

##Attributes
####resourceType (opt., string)
The resource type that is being priced. For example : "vm", "storage" etc.

_Ref uri : to https://olivierbelli.github.io/#resourceType_

####name (opt., string)
The name of the resource. For example "t2.micro", "Amazon Egress Networking" etc.

_Ref uri : https://schema.org/name_

####descriptionVector (opt., object)
Vector used to describe the resource. Examples : for static instances, the vector would be {"vcpu" : 2, "ram" : 4, "disk" : 10}. For fixed-size disks {"disk" : 1024} etc. If the resource is dynamic (i.e. it can take all the values), this vector is empty.

_Ref uri : https://olivierbelli.github.io/#descriptionVector_

####price (req., number)
The price per unit of the a cloud resource (VM, network, disk etc.).

_Ref uri : https://schema.org/price_

####priceCurrency (req., string)
The currency for the price. This must be the 3 letter currency code from the ISO 4217 standard. The list in XML format is available from: http://www.currency-iso.org/dam/downloads/lists/list_one.xml.

_Ref uri : https://schema.org/priceCurrency_

####unitCode (req., string)
The UN/CEFACT 3 letter code for the units of the resource. The link to these codes is: http://www.unece.org/fileadmin/DAM/cefact/recommendations/rec20/rec20_rev3_Annex3e.pdf.
Other standards can be used by prefixing the value and separating the value from the unit with a colon, for example, si:Kg for the International System Kilogram measure.
If there is no unit associated with the resource being priced, then use the value "C62" (no unit) or do not specify this attribute. Alternatively, if the resource is unit-less but is priced by the hundreds, thousands, etc., then provide the number.

_Ref uri : https://schema.org/unitCode_

####billingTimeCode (req., string)
Value describing the billing period for the item. Note that this value also indicates when to reset the discounts. This should follow the same conventions as for unitCode. If the price is not time-based (for example charged per unit of something), then this should use the value "C62" (no unit) or do not specify this attribute. > per Year = ANN, per Month = MON, per Week = WEE, per Day = DAY, per Hour = HUR, per Minute = MIN, per Second = SEC.

_Ref uri : https://olivierbelli.github.io/#timeCode_

####sampleTimeCode (req., string)
Value which is the smallest billing unit. This should follow the same conventions as for unitCode. If the price is not time-based (for example charged per unit of something), then this should use the value "C62" (no unit) or do not specify this attribute. > per Year = ANN, per Month = MON, per Week = WEE, per Day = DAY, per Hour = HUR, per Minute = MIN, per Second = SEC.

_Ref uri : https://olivierbelli.github.io/#timeCode_


####discount (opt, object)
A list of attributes describing the way to apply discounts on the resource. Attributes are :

_Ref uri : https://olivierbelli.github.io/#discount_

>	**method (opt., string)**
	
>	Method to use for calculating the discount. Supported values are "Progressive", "Reservation", and "None". "None" is the default if the attribute is not present.

> 	_Ref uri : to https://olivierbelli.github.io/#discount.method_

>	**steps (opt., array)**
	
>	Additional information for calculating a pricing discount. This is an array of pairs of numeric values that depend on the charge method indicated by the discount method, unitCode and timeCode.

> 	_Ref uri : https://olivierbelli.github.io/#discount.steps_

>	**:warning: _Deperecated_ :warning: reset (opt., boolean)**
	
>	Value to describe if the discount has a maximum or if it reset after the last value (circling).

> 	_Ref uri : https://olivierbelli.github.io/#discount.reset_


####freeQuantity (opt., number)
The value below which the use of the resource is free. The default value is zero.

_Ref uri : to https://olivierbelli.github.io/#freeQuantity_

####associatedCosts (opt., array)
An array of associated costs described by the same schema. This attribute is used to describe more complex resources that are priced by multiple factors (i.e. storage + number of i/o requests)

_Ref uri : https://github.com/SixSq/pricing-tool/blob/master/Schema.md_
