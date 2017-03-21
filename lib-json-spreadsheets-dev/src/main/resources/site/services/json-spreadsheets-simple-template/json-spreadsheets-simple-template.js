var jsonSpreadSheets = require("/lib/json-spreadsheets");
var contentLib = require('/lib/xp/content');
var encodingLib = require('/lib/text-encoding');
var portalLib = require('/lib/xp/portal');

/**
 *
 *
 *
 */

function handleRequest( req ) {
    var config = {
        reportFileName : "json-spreadsheet-simple.xls",
        template: {
            resourceSimple: resolve('./simple_export_template.xlsx')
        },
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


    log.info('config in service: %s', JSON.stringify( config ) );

    // Create the report and get the result
    var jsonSpreadSheetsResult = jsonSpreadSheets.getSpreadsheet( config );

    return {
        body: jsonSpreadSheets.getDataAsStream( jsonSpreadSheetsResult ),
        headers : {
            "Content-Disposition": 'attachment; filename="' + sconfig.reportFileName + '"'
        }
    };
};

exports.get = handleRequest;