var jsonSpreadSheetsBean =  __.newBean("no.rbrastad.json.spreadsheets.JSONSpreadsheets");

exports.getSpreadsheet = function( config ) {
    try {
        return JSON.parse(jsonSpreadSheetsBean.getSpreadsheet(JSON.stringify(config)));
    }catch (e){
        return e;
    }
};

