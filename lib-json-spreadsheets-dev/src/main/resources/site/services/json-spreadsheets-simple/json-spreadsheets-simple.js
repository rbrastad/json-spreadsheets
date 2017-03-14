var jsonSpreadSheets = require("/lib/json-spreadsheets");
var contentLib = require('/lib/xp/content');
var encodingLib = require('/lib/text-encoding');
var portalLib = require('/lib/xp/portal');
/**
 *
 *  Create a simple excel sheet from JSON
 *
 */

function handleRequest( req ) {
    var jsonSpreadsheetConfig = {
        fileName : "json-spreadsheet-test.xls",
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


    // Create the report Get the result
    var jsonSpreadSheetsResult = jsonSpreadSheets.getSpreadsheet( jsonSpreadsheetConfig );

    // Decode the data string
    var decodedStream = encodingLib.base64Decode(jsonSpreadSheetsResult.data);

    // create a media content  somewhere
    // http://repo.enonic.com/public/com/enonic/xp/docs/6.9.3/docs-6.9.3-libdoc.zip!/module-lib_xp_content.html#.createMedia
    var attachment = contentLib.createMedia({
        name: jsonSpreadsheetConfig.fileName,
        parentPath: "/",
        mimeType: 'text/plain',
        data: decodedStream,
        branch: 'draft'
    });

    // Create the url so we can download the the file
    var attachmentUrl = portalLib.attachmentUrl({
        path: attachment._path,
        download: true,
        type : "absolute"
    });

    // Do something with the response
    delete jsonSpreadSheetsResult.data;
    jsonSpreadSheetsResult.attachment = attachment;
    jsonSpreadSheetsResult.attachment.downloadUrl = attachmentUrl;

    return {
        status : 201,
        body: jsonSpreadSheetsResult,
        contentType: "application/json"
    };
};

exports.get = handleRequest;