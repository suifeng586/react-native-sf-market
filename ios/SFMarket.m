//
//  SFMarket.m
//  SFMarket
//
//  Created by rw on 2018/6/8.
//  Copyright © 2018年 Facebook. All rights reserved.
//

#import "SFMarket.h"
#import <StoreKit/StoreKit.h>
#import <UIKit/UIKit.h>
@interface SFMarket()

@end
@implementation SFMarket
-(void)evaScore:(NSString *)appId cb:(void(^)(BOOL isSuccess))cb{
  if([SKStoreReviewController respondsToSelector:@selector(requestReview)]) {// iOS 10.3 以上支持
    //防止键盘遮挡
    [[UIApplication sharedApplication].keyWindow endEditing:YES];
    [SKStoreReviewController requestReview];
    cb(true);
  }else{
    NSString  * nsStringToOpen = [NSString  stringWithFormat: @"itms-apps://itunes.apple.com/app/id%@?action=write-review",appId];
    BOOL ret = [[UIApplication sharedApplication] openURL:[NSURL URLWithString:nsStringToOpen]];
    cb(ret);
  }
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(evaScore:(NSString *)appId callBack:(RCTResponseSenderBlock)callback)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    [self evaScore:appId cb:^(BOOL isSuccess) {
      if (isSuccess){
        callback(@[[NSNull null],@""]);
      }else{
        callback(@[@"-1",@""]);
      }
    }];
  });
}

@end
