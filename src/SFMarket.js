
/**
 * Created by Joker on 2017-08-17.
 */
import {
    Platform,
    NativeModules
} from 'react-native'
var _market = NativeModules.SFMarket;
const SFMarket = {
    evaScore:function (appId,callBack) {
        if (Platform.OS == 'ios'){
            _market.evaScore(appId,(err,msg)=>{
                if (err){
                    callBack(true);
                }else{
                    callBack(false);
                }
            });
        }else{
            _market.gotoMarket((err,msg)=>{
                if (err){
                    callBack(true);
                }else{
                    callBack(false);
                }
            });
        }

    }

};
module.exports = SFMarket;