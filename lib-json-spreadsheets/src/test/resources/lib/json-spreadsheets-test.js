var assert = require('/lib/xp/assert');

var jsonSpreadSheets = require("/lib/json-spreadsheets");

exports.test = function () {
    testSimpleReport();
    testSimpleTemplateReport();
    testObjectCollectionReport();
    testWorkDirectoryReport();
};


function testSimpleReport() {

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

    log.info( "******** testSimpleReport ******** ");
    log.info( JSON.stringify( jsonSpreadSheetsResult, null,2));

    if(jsonSpreadSheetsResult.hasOwnProperty("data")){
        if( jsonSpreadSheetsResult.data != "")
            jsonSpreadSheetsResult.data = "data";
    }

    assert.assertJsonEquals({
        "length": 0,
        "uuid": null,
        "data": "data",
        "config": {
            "reportFileName": "json-spreadsheet-simple.xls",
            "header": [
                {
                    "name": "name",
                    "title": "Name",
                    "type": "string"
                },
                {
                    "name": "actor",
                    "title": "Actor",
                    "type": "string"
                }
            ],
            "data": [
                {
                    "name": "Lokiiiiiiiiiiiii",
                    "actor": "Tom Middleston"
                },
                {
                    "name": "Thor",
                    "actor": "Chris Mensworth"
                },
                {
                    "name": "Jane",
                    "actor": "Natalie Portman"
                },
                {
                    "name": "Malekith",
                    "actor": "Christopher Eccleston"
                }
            ]
        }
    },jsonSpreadSheetsResult);
}




function testSimpleTemplateReport() {

    var config = {
        reportFileName : "json-spreadsheet-simple.xls",
        template: {
            resourceSimple: resolve('templates/SimpleReport.xls')
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

    // Create the report and get the result
    var jsonSpreadSheetsResult = jsonSpreadSheets.getSpreadsheet( config );

    log.info( "******** testSimpleTemplateReport ******** ");
    log.info( JSON.stringify( jsonSpreadSheetsResult, null,2));

    assert.assertJsonEquals({},{});
}



function testObjectCollectionReport() {

    var config = {
        reportFileName : "object_collection_template_result.xls",
        template:{
            resource: resolve('templates/object_collection_template.xls')
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

    log.info( "******** testObjectCollectionReport ******** ");
    log.info( JSON.stringify( jsonSpreadSheetsResult, null,2));

    if(jsonSpreadSheetsResult.hasOwnProperty("data")){
        if( jsonSpreadSheetsResult.data != "")
            jsonSpreadSheetsResult.data = "data";
    }

    assert.assertJsonEquals({},{});
}



function testWorkDirectoryReport() {

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

    log.info( "******** testWorkDirectoryReport ******** ");
    log.info( JSON.stringify( jsonSpreadSheetsResult, null,2));

    if(jsonSpreadSheetsResult.hasOwnProperty("data")){
        if( jsonSpreadSheetsResult.data != "")
            jsonSpreadSheetsResult.data = "data";
    }


    assert.assertJsonEquals({},{});
}
