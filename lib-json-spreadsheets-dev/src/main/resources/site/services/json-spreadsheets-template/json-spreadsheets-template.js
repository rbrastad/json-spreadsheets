var jsonSpreadSheets = require("/lib/json-spreadsheets");
var contentLib = require('/lib/xp/content');
var encodingLib = require('/lib/text-encoding');
var portalLib = require('/lib/xp/portal');
var ioLib = require('/lib/xp/io');

/**
 *
 *  Create a excel sheet from a Template and add the json data
 *
 */

function handleRequest( req ) {
    var config = {
        reportFileName : "object_collection_template_result.xls",
        template:{
          resource: resolve('./object_collection_template.xls')
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
        }
     };

    // Create the report
    var jsonSpreadSheetsResult = jsonSpreadSheets.getSpreadsheet( config );

    // Return the
    return {
        body: jsonSpreadSheets.getDataAsStream( jsonSpreadSheetsResult ),
        headers : {
            "Content-Disposition": 'attachment; filename="' + config.reportFileName + '"'
        }
    };
};

exports.get = handleRequest;