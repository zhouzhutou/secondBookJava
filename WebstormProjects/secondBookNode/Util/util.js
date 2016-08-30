var ejs=require('ejs');
var moment=require('../public/moment/moment');
exports.dateFormat=function (obj,format_) {
    if(format==undefined)
    {
        format_='YYYY';
    }
    return moment(obj.toISOString),format(format_);
}