Characteristics of the CSP
==========================

Implicitly all of the standard CIMI common attributes are available
for the entry, this includes a human-readable name and descriptions,
along with other general attributes.

connector (req., string)
------------------------
Name of the connector to which this entry refers.

country (req., string)
----------------------

The ISO 3166-1 alpha-2 country code where the cloud infrastructure
physically resides.

See: https://www.iso.org/obp/ui/#search

location (opt., string)
-----------------------

Textual representation of the location where the cloud infrastructure
physically resides.

supplierUrl (opt., string)
--------------------------

URL that provides more information about the supplier or the
supplier's offer(s).  Intended as a resource for humans to obtain more
information. 

cpuCapacity (req., int)
-----------------------

Maximum number of CPUs (CPU cores) that are available on the
supplier's infrastructure. 

ramCapacityGib (req., int)
--------------------------

Maximum amount of RAM that is available on the supplier's
infrastructure in Gibibits (Gib).

storageCapacityTB (req., int)
-----------------------------

Maximum amount of volatile storage that is available on the supplier's
infrastructure in Terabytes (TB). 


Characteristics of a VM Offer
=============================

offerIdentifier (req., string)
------------------------------

A unique string (for a supplier) that unabiguously identifies this
offer.

minCpu (opt., int), maxCpu (req., int)
--------------------------------------

The minimum and maximum values for the CPU that can be allocated with
this offer.  If the minimum is missing, zero is assumed.  If the value
is fixed, then the minimum and maximum should have the same value.

minRamMiB (opt., int), maxRamMib (req., int)
--------------------------------------------

The minimum and maximum values for the RAM that can be allocated with
this offer.  If the minimum is missing, zero is assumed.  If the value
is fixed, then the minimum and maximum should have the same value.

minStorageGB (opt., int), maxStorageGB (req., int)
--------------------------------------------------

The minimum and maximum values for the **volatile** storage that can
be allocated with this offer.  If the minimum is missing, zero is
assumed.  If the value is fixed, then the minimum and maximum should
have the same value.

ramPriceModel, cpuPriceModel, storagePriceModel (opt., price model)
-------------------------------------------------------------------

See the pricing attributes. 

associatedCosts (opt., array of price model)
--------------------------------------------

Costs for other resources associated with the VM that are priced
separately.  Should not be used for the RAM, CPU, or Storage costs. 


Price Model
-----------

Note that these attributes can be used directly at the "offer" level
if there is one price for the combined offer (typically when certain
VM configurations are presented as discrete choices).  If the offer
price must be composed from individual component prices, then these
attributes should appear in the component pricing model attributes.

resourceType (opt., string)
---------------------------

The resource type that is being priced.

price (req., number)
--------------------

The price per unit of the VM or VM component.

priceCurrency (req., string)
----------------------------

The currency for the price.  This must be the 3 letter currency code
from the ISO 4217 standard.  The list in XML format is available from:
http://www.currency-iso.org/dam/downloads/lists/list_one.xml. 

unitCode (req., string)
-----------------------

The UN/CEFACT 3 letter code for the units of the resource.  The link
to these codes is:
http://www.unece.org/fileadmin/DAM/cefact/recommendations/rec20/rec20_rev3_Annex3e.pdf.

Other standards can be used by prefixing the value and separating the
value from the unit with a colon, for example, si:Kg for the
International System Kilogram measure.

If there is no unit associated with the resource being priced, then
use the value "C62" (no unit) or do not specify this attribute.
Alternatively, if the resource is unit-less but is priced by the
hundreds, thousands, etc., then provide the number. 

timeCode (req., string)
-----------------------

The time period associated with the price.  This should follow the
same conventions as for unitCode.  If the price is not time-based (for
example charged per unit of something), then this should use the value
"C62" (no unit) or do not specify this attribute.

discountMethod (opt., string)
-----------------------------

Method to use for calculating the discount. Supported values are
"Stair Steps", "Fixed Amount", and "None".  "None" is the default if
the attribute is not present. 

discountSteps (opt., array)
---------------------------

Additional information for calculating a pricing discount.  This is an
array of pairs of numeric values that depend on the charge method
indicated by the discountMethod, unitCode and timeCode. 

freeQuantity (opt., number)
---------------------------

The value below which the use of the resource is free.  The default
value is zero. 
