var jsonSpreadSheets = require("/lib/json-spreadsheets");
var contentLib = require('/lib/xp/content');
var encodingLib = require('/lib/text-encoding');
var portalLib = require('/lib/xp/portal');

/**
 *
 *  Create a simple excel sheet firm aJSON header and data and with the default built in template
 *
 */

function handleRequest( req ) {
    var config = {
        reportFileName : "json-spreadsheet-simple.xls",
        header: [
            {
                name: "name",
                title: "Name",
                type: "string"
            },
            {
                name: "actor",
                title: "Actor",
                type: "string"
            }
        ],
        data: [
            {
                name: "Lokiiiiiiiiiiiii",
                actor: "Tom Middleston"
            },
            {
                name: "Thor",
                actor: "Chris Mensworth"
            },
            {
                name: "Jane",
                actor: "Natalie Portman"
            },
            {
                name: "Malekith",
                actor: "Christopher Eccleston"
            }
        ]
    };

    // Create the report and get the result
    var jsonSpreadSheetsResult = jsonSpreadSheets.getSpreadsheet( config );

    return {
        body: jsonSpreadSheets.getDataAsStream( jsonSpreadSheetsResult ),
        headers : {
            "Content-Disposition": 'attachment; filename="' + config.reportFileName + '"'
        }
    };
};

exports.get = handleRequest;