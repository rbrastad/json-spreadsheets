var jsonSpreadSheetsBean =  __.newBean("no.rbrastad.json.spreadsheets.JSONSpreadsheets");
var ioLib = require('/lib/xp/io');
var encodingLib = require('/lib/text-encoding');

exports.getSpreadsheet = function( config ) {
    try {
        if(config.hasOwnProperty("template")){
            if( config.template.hasOwnProperty("stream") ) {
                return JSON.parse(jsonSpreadSheetsBean.getSpreadsheet(JSON.stringify(config), config.template.stream));
            }else if(config.template.hasOwnProperty("resource")){
                var stream = exports.getResourceStream( config.template.resource );
                if(stream) {
                    return JSON.parse(jsonSpreadSheetsBean.getSpreadsheet(JSON.stringify(config), stream));
                }else{
                    return {"error": "Template: " + config.template.resource + " NOT found" };
                }
            }else if( config.hasOwnProperty("work") ) {
                return JSON.parse(jsonSpreadSheetsBean.getSpreadsheet(JSON.stringify(config), null));
            }
        }else
            return JSON.parse(jsonSpreadSheetsBean.getSpreadsheet(JSON.stringify(config)));
    }catch (e){
        return e;
    }
};

exports.getResourceStream = function( resource ){
    var template = ioLib.getResource( resource );
    log.debug('getResourceStream - resource: %s', resource );
    if (template.exists()) {
        log.debug('getResourceStream - template: %s', JSON.stringify( template ) );
        return template.getStream();
    }else {
        log.debug('getResourceStream - template resource NOT FOUND: %s', resource );
        return false;
    }
}


exports.getDataAsStream = function( result ){
   return encodingLib.base64Decode(result.data);
}