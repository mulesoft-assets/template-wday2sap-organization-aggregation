
# Anypoint Template: Workday and SAP Organization Aggregation	

<!-- Header (start) -->
Collects and merges organizations from Workday and SAP into a CSV file. You can modify this basic pattern to collect from one or more different sources and produce formats other than CSV. You can trigger this manually, or programmatically with an HTTP call. 

Workers are sorted such that the ones only in Workday appear first, followed by those only in SAP, and lastly by the those in both systems. The custom sort or merge logic can be easily modified to present the data as needed. This template also serves as a base for building APIs using the Anypoint Platform.

Aggregation templates can be easily extended to return a multitude of data in mobile friendly form to power your mobile initiatives by providing easily consumable data structures from otherwise complex backend systems.

![a6bc2b8a-f3d1-49bf-bd38-70d473bd10b0-image.png](https://exchange2-file-upload-service-kprod.s3.us-east-1.amazonaws.com:443/a6bc2b8a-f3d1-49bf-bd38-70d473bd10b0-image.png)

<!-- Header (end) -->

# License Agreement
This template is subject to the conditions of the <a href="https://s3.amazonaws.com/templates-examples/AnypointTemplateLicense.pdf">MuleSoft License Agreement</a>. Review the terms of the license before downloading and using this template. You can use this template for free with the Mule Enterprise Edition, CloudHub, or as a trial in Anypoint Studio. 
# Use Case
<!-- Use Case (start) -->
As a SAP admin I want to aggregate organizations from Workday and from SAP and compare them to see which organizations can only be found in one of the two and which organizations are in both instances. 

This template:

- Generates its results in a CSV report sent by email.
- Extracts data from two systems, aggregates data, compares values of fields for the objects, and generates a report on the differences.
- Gets organizations from Workday and from SAP, compares by the name of the organizations, generates a CSV file which shows organizations in Workday and in SAP. The report is then emailed to a configured group of email addresses.
<!-- Use Case (end) -->

# Considerations
<!-- Default Considerations (start) -->

<!-- Default Considerations (end) -->

<!-- Considerations (start) -->
To make this template run, there are certain preconditions that must be considered. All of them deal with the preparations in both, that must be made for the template to run smoothly. Failing to do so can lead to unexpected behavior of the template.

Before continuing to use this template, see the [SAP connector guide](https://docs.mulesoft.com/connectors/sap/sap-connector) to work with SAP and Anypoint Studio.
## Disclaimer
This template uses a few private Maven dependencies from MuleSoft ito work. If you intend to run this template with Maven support, you need to add three extra dependencies for SAP to the pom.xml. See [SAP XML and Maven Support](https://docs.mulesoft.com/connectors/sap/sap-connector#xml-and-maven-support).
<!-- Considerations (end) -->


## SAP Considerations

Here's what you need to know to get this template to work with SAP.


### As a Data Destination

There are no considerations with using SAP as a data destination.

## Workday Considerations

### As a Data Source

The Workday connector currently does not support autopaging functionality out of the box so number of processed objects are limited to the connector's single page size.

# Run it!
Simple steps to get this template running.
<!-- Run it (start) -->

<!-- Run it (end) -->

## Running On Premises
Complete all properties in one of the property files, for example in mule.prod.properties and run your app with the corresponding environment variable to use it. To follow the example, use `mule.env=prod`.

After this, to trigger the use case you just need to browse to the local http endpoint with the port you configured in your file. If this is, for instance, `9090` then you should browse to: `http://localhost:9090/generatereport` and this will create a CSV report and send it to the mails set.
<!-- Running on premise (start) -->

<!-- Running on premise (end) -->

### Where to Download Anypoint Studio and the Mule Runtime
If you are new to Mule, download this software:

+ [Download Anypoint Studio](https://www.mulesoft.com/platform/studio)
+ [Download Mule runtime](https://www.mulesoft.com/lp/dl/mule-esb-enterprise)

**Note:** Anypoint Studio requires JDK 8.
<!-- Where to download (start) -->

<!-- Where to download (end) -->

### Importing a Template into Studio
In Studio, click the Exchange X icon in the upper left of the taskbar, log in with your Anypoint Platform credentials, search for the template, and click Open.
<!-- Importing into Studio (start) -->

<!-- Importing into Studio (end) -->

### Running on Studio
After you import your template into Anypoint Studio, follow these steps to run it:

1. Locate the properties file `mule.dev.properties`, in src/main/resources.
2. Complete all the properties required per the examples in the "Properties to Configure" section.
3. Right click the template project folder.
4. Hover your mouse over `Run as`.
5. Click `Mule Application (configure)`.
6. Inside the dialog, select Environment and set the variable `mule.env` to the value `dev`.
7. Click `Run`.

For this template to run in Anypoint Studio, you need to [install SAP Libraries](https://docs.mulesoft.com/connectors/sap/sap-connector#install-sap-libraries).
<!-- Running on Studio (start) -->

<!-- Running on Studio (end) -->

### Running on Mule Standalone
Update the properties in one of the property files, for example in mule.prod.properties, and run your app with a corresponding environment variable. In this example, use `mule.env=prod`. 


## Running on CloudHub
When creating your application in CloudHub, go to Runtime Manager > Manage Application > Properties to set the environment variables listed in "Properties to Configure" as well as the mule.env value.
<!-- Running on Cloudhub (start) -->
Once your app is all set and started, supposing you choose as domain name `workdayorganizationsaggregation` to trigger the use case you just need to browse to `http://workdayorganizationsaggregation.cloudhub.io/generatereport` and the report is sent to the emails configured.
<!-- Running on Cloudhub (end) -->

### Deploying a Template in CloudHub
In Studio, right click your project name in Package Explorer and select Anypoint Platform > Deploy on CloudHub.
<!-- Deploying on Cloudhub (start) -->

<!-- Deploying on Cloudhub (end) -->

## Properties to Configure
To use this template, configure properties such as credentials, configurations, etc.) in the properties file or in CloudHub from Runtime Manager > Manage Application > Properties. The sections that follow list example values.
### Application Configuration
<!-- Application Configuration (start) -->
#### Application Configuration	

- http.port `9090`

#### Workday Connector Configuration

- wday.username `bob.dylan@orga`
- wday.tenant `org457`
- wday.password `DylanPassword123`
- wday.host `servise425546.workday.com`

### SAP Connector Configuration

- sap.jco.ashost `your.sap.address.com`
- sap.jco.user `SAP_USER`
- sap.jco.passwd `SAP_PASS`
- sap.jco.sysnr `14`
- sap.jco.client `800`
- sap.jco.lang `EN`

#### SMTP Services Configuration

- smtp.host `smtp.gmail.com`
- smtp.port `587`
- smtp.user `exampleuser@gmail.com`
- smtp.password `ExamplePassword456`

#### Email Details

- mail.from `exampleuser@gmail.com`
- mail.to `woody.guthrie@gmail.com`
- mail.subject `Organization Report`
- mail.body `Please find attached your Organization Report`
- attachment.name `OrganizationsReport.csv`
<!-- Application Configuration (end) -->

# API Calls
<!-- API Calls (start) -->
There are no special considerations regarding API calls.
<!-- API Calls (end) -->

# Customize It!
This brief guide provides a high level understanding of how this template is built and how you can change it according to your needs. As Mule applications are based on XML files, this page describes the XML files used with this template. More files are available such as test classes and Mule application files, but to keep it simple, we focus on these XML files:

* config.xml
* businessLogic.xml
* endpoints.xml
* errorHandling.xml
<!-- Customize it (start) -->

<!-- Customize it (end) -->

## config.xml
<!-- Default Config XML (start) -->
This file provides the configuration for connectors and configuration properties. Only change this file to make core changes to the connector processing logic. Otherwise, all parameters that can be modified should instead be in a properties file, which is the recommended place to make changes.
<!-- Default Config XML (end) -->

<!-- Config XML (start) -->

<!-- Config XML (end) -->

## businessLogic.xml
<!-- Default Business Logic XML (start) -->
The functional aspect of this template is implemented in this XML file, directed by one flow responsible of conducting the aggregation of data, comparing records and finally formatting the output, in this case being a report.
Using Scatter-Gather component we are querying the data in different systems. After that the aggregation is implemented in DataWeave 2 script using Transform component.

Aggregated results are sorted by source of existence:

1. Organizations only in Workday
2. Organizations only in SAP
3. Organizations in both Workday and SAP
and transformed to CSV format. Final report in CSV format is sent to email, that you configured in mule.*.properties file.<!-- Default Business Logic XML (end) -->

<!-- Business Logic XML (start) -->

<!-- Business Logic XML (end) -->

## endpoints.xml
<!-- Default Endpoints XML (start) -->
This file provides the endpoint to start the aggregation. This template has an HTTP Listener as the way to trigger the use case.
### Trigger Flow

**HTTP Inbound Endpoint** - Start Report Generation

- `${http.port}` is set as a property to be defined either on a property file or in CloudHub environment variables.
- The path configured by default is `generatereport` and you are free to change for the one you prefer.
- The host name for all endpoints in your CloudHub configuration should be defined as `localhost`. CloudHub will then route requests from your application domain URL to the endpoint.
<!-- Default Endpoints XML (end) -->

<!-- Endpoints XML (start) -->

<!-- Endpoints XML (end) -->

## errorHandling.xml
<!-- Default Error Handling XML (start) -->
This file handles how your integration reacts depending on the different exceptions. This file provides error handling that is referenced by the main flow in the business logic.
<!-- Default Error Handling XML (end) -->

<!-- Error Handling XML (start) -->

<!-- Error Handling XML (end) -->

<!-- Extras (start) -->

<!-- Extras (end) -->
