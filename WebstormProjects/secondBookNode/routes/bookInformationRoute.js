'use strict';
var express=require('express');
var ejs=require('ejs');
var util=require('../Util/util');
var router=express.Router();
var AV=require('leanengine');
var param=require('../Util/parameter');
var sellingBook=AV.Object.extend('SecondBook_Book_SellingBook');
var wishBook=AV.Object.extend('SecondBook_User_Wish');
var user=AV.Object.extend('_User');
/*var WishBookInfo=AV.Object.extend('SecondBook_User_Wish');*/
router.use('/sale/:bookId',function(req,res,next)
{
    var bookId=req.params.bookId;
    console.log(bookId);
    var query=new AV.Query(sellingBook);
    query.equalTo('objectId',bookId);
    query.include('poiDependentUser');
    query.find().then(function(sellingBooks){
        var sellingBookOne=sellingBooks[0];
        console.log(typeof sellingBookOne);
       if(typeof sellingBookOne==='undefined')
        {
            var message="No book found！";
            var error={};
            error.status=param.errorCode.BOOK_NOT_FOUND;
            error.message=message;
            res.render('bookError',{message:message,error:error});
        }else {
        var user=sellingBookOne.get('poiDependentUser');
        var userId=user.get('objectId');
            var phone=user.get('strPhoneNumber');
            var name=user.get('username');
            var urls=new Array();
            for(var i=1;i<=3;i++)
            {
                urls[i]='';
                //console.log(typeof sellingBookOne.get('filPicture'+i)==='object');
                if(typeof sellingBookOne.get('filPicture'+i)==='object')
                {
                    urls[i]+=sellingBookOne.get('filPicture'+i).get('url');
                    console.log(urls[i]);
                }
            }
            console.log(urls[2]);
            res.render('bookInformationSale',{sellingBookOne:sellingBookOne,name:name,phone:phone,urls:urls});
       // }
    }},function (err) {
        if(err.code===101)
        {
            var message='找不到请求的图书！';
            res.status(err.code);
            res.render('bookError',{message:message,error:err});
        }else {
            next(err);
        }
    }).catch(next);
});
router.use('/wish/:bookId',function(req,res,next){
    var bookId=req.params.bookId;
    var query=new AV.Query(wishBook);
    query.equalTo('objectId',bookId);
    query.include('poiDependentUser');
    query.find().then(function(wishBooks){
        var wishBookOne=wishBooks[0];
        if(typeof wishBookOne==='undefined')
        {
            var message="No book found！";
            var error={};
            error.status=param.errorCode.BOOK_NOT_FOUND;
            error.message=message;
            res.render('bookError',{message:message,error:error});
        }else{
        var user=wishBookOne.get('poiDependentUser');
        var name=user.get('username');
        var phone=user.get('strPhoneNumber');
        var urls=new Array();
        for(var i=1;i<=3;i++)
        {
            urls[i]='';
            if(typeof wishBookOne.get('filPicture'+i)==='object')
            {
                urls[i]+=wishBookOne.get('filPicture'+i).get('url');
            }
        }
        res.render('bookInformationWill',{wishBookOne:wishBookOne,name:name,phone:phone,urls:urls});
    }},function(err)
    {
       if(err.code===101)
       {
           err.message='找不到指定请求的图书！';
           res.status(err.code);
           res.render('bookError',{message:err.message,error:err});
       }else{
           next(err);
       }
    }).catch(next);
});
module.exports=router;
