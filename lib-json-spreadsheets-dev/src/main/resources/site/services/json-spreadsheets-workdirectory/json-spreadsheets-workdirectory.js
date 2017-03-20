var jsonSpreadSheets = require("/lib/json-spreadsheets");
var contentLib = require('/lib/xp/content');
var encodingLib = require('/lib/text-encoding');
var portalLib = require('/lib/xp/portal');
var ioLib = require('/lib/xp/io');

/**
 *
 *  Create a excel sheet from a Template with data JSON
 *
 */

function handleRequest( req ) {
    var config = {
        reportFileName : "object_collection_template_result.xls",
        template:{
            // The template filename must be in the work.templatesDirectory value
            fileName: "object_collection_template.xls"
        },
        data: {
            "title":"Employees",
            "employees":[
                {
                    "name":"Loki",
                    "birthDate":"01.02.03"
                },
                {
                    "name":"Thor",
                    "birthDate":"01.02.04"
                },
                {
                    "name":"Jane",
                    "birthDate":"01.02.05"
                },
                {
                    "name":"Malekith",
                    "birthDate":"01.02.06"
                }
            ]
        },
        // If the work directory is present. The configuration is used for reading templates and writing reports
        work :{
            // Where created reports are created
            reportsDirectory: "/home/rbrastad/Downloads/json-spreadsheet-work/reports",
            // Where report templates are read from.
            templatesDirectory: "/home/rbrastad/Downloads/json-spreadsheet-work/templates",
        }
    };

    // Create the report Get the result
    var jsonSpreadSheetsResult = jsonSpreadSheets.getSpreadsheet( config );

    delete jsonSpreadSheetsResult.data;
    delete jsonSpreadSheetsResult.length;

    return {
        body: jsonSpreadSheetsResult,
        contentType: "application/json"
    };
};

exports.get = handleRequest;

