/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import java.util.ResourceBundle;
import java.util.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author edrp-07
 */
public class Traker {


    public static String getActivity(String id,String lang)
    {
        Locale locale=null;
   String locale1="en";
         try{

        locale1=(String)lang;
    if(lang!=null)
    {
        locale1 = (String)lang;
     }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale("en");

    if(!(locale1.equals("ur")||locale1.equals("ar"))){ }
    else{ }

 ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

String t="";
int x=Integer.parseInt(id);

switch(x){
    case 1:
            t="Administrator->Manage Staff->Register Staff";
            break;

    case 2:
         t="Administrator->Manage Staff->Change Staff";
            break;

    case 3:
        t="Administrator->Manage Staff->Delete Staff";
            break;

    case 4:
         t="Administrator->Manage Staff->View Staff";
            break;
  case 5:
         t="Administrator->Manage Staff->View All Staff";
            break;
  case 6:
         t="Administrator->Manage Staff Account->Create Staff Account";
            break;
  case 7:
         t="Administrator->Manage Staff Account->View Staff Account";
            break;
  case 8:
         t="Administrator->Manage Staff Account->Update Account";
            break;
  case 9:
         t="Administrator->Manage Staff Account->Delete Staff Account";
            break;
  case 10:
         t="Administrator->Manage Staff Account->View All Staff Account";
            break;
  case 11:
         t="Administrator->Manage Staff Privilege->Assign Privileges";
            break;
  case 12:
         t="Administrator->Manage Staff Privilege->Change Privileges";
            break;
  case 13:
         t="Administrator->Manage Staff Privilege->View Privileges";
            break;


   case 100:
            t=resource.getString("admin.header.main3");
            break;
   case 101:
            t=resource.getString("admin.header.acquisition71");
            break;
   case 102:
            t=resource.getString("admin.header.acquisition16");
            break;
   case 103:
      t=resource.getString("admin.header.acquisition17");
      break;
   case 104:
      t=resource.getString("admin.header.acquisition18");
      break;
   case 105:
      t=resource.getString("admin.header.acquisition19");
      break;
   case 106:
      t=resource.getString("admin.header.acquisition72");
      break;
   case 107:
      t=resource.getString("admin.header.acquisition20");
      break;
   case 108:
      t=resource.getString("admin.header.acquisition21");
      break;
   case 109:
      t=resource.getString("admin.header.acquisition22");
      break;
   case 110:
      t=resource.getString("admin.header.acquisition1");
      break;
   case 111:
      t=resource.getString("admin.header.acquisition2");
      break;
   case 112:
      t=resource.getString("admin.header.acquisition23") ;
      break;
    case 113:
      t=resource.getString("admin.header.acquisition3") ;
      break;
     case 114:
      t=resource.getString("admin.header.acquisition2");
      break;
      case 115:
      t=resource.getString("admin.header.acquisition24") ;
      break;
      case 116:
      t=resource.getString("admin.header.acquisition5") ;
      break;
      case 117:
      t=resource.getString("admin.header.acquisition6") ;
      break;
      case 118:
      t=resource.getString("admin.header.acquisition7") ;
      break;
      case 119:
      t=resource.getString("admin.header.acquisition8") ;
      break;
      case 120:
      t=resource.getString("admin.header.acquisition9") ;
      break;
      case 121:
      t=resource.getString("admin.header.acquisition73") ;
      break;
      case 122:
      t=resource.getString("admin.header.acquisition25") ;
      break;
      case 123:
      t=resource.getString("admin.header.acquisition1") ;
      break;
      case 124:
      t=resource.getString("admin.header.acquisition11") ;
      break;
      case 125:
      t=resource.getString("admin.header.acquisition26") ;
      break;
      case 126:
      t=resource.getString("admin.header.acquisition27") ;
      break;
      case 127:
      t=resource.getString("admin.header.acquisition28") ;
      break;

      case 128:
      t=resource.getString("admin.header.acquisition29") ;
      break;
      case 129:
      t=resource.getString("admin.header.acquisition74") ;
      break;
      case 130:
      t=resource.getString("admin.header.acquisition75");
      break;
      case 131:
      t=resource.getString("admin.header.acquisition30") ;
      break;
      case 132:
      t=resource.getString("admin.header.acquisition31") ;
      break;
      case 133:
      t=resource.getString("admin.header.acquisition32") ;
      break;
      case 134:
      t=resource.getString("admin.header.acquisition33") ;
      break;
      case 135:
      t=resource.getString("admin.header.acquisition12") ;
      break;
      case 136:
      t=resource.getString("admin.header.acquisition13") ;
      break;
      case 137:
      t=resource.getString("admin.header.acquisition14");
      break;
      case 138:
      t=resource.getString("admin.header.acquisition15");
      break;
      case 139:
      t=resource.getString("admin.header.acquisition76") ;
      break;
      case 140:
      t=resource.getString("admin.header.acquisition34") ;
      break;
      case 141:
      t=resource.getString("admin.header.acquisition35") ;
      break;
      case 142:
      t=resource.getString("admin.header.acquisition77") ;
      break;
      case 143:
      t=resource.getString("admin.header.acquisition78") ;
      break;

      case 144:
      t=resource.getString("admin.header.acquisition79");
      break;
      case 145:
      t=resource.getString("admin.header.acquisition36") ;
      break;
      case 146:
      t=resource.getString("admin.header.acquisition37");
      break;
      case 147:
      t=resource.getString("admin.header.acquisition38");
      break;
      case 148:
      t=resource.getString("admin.header.acquisition80") ;
      break;
      case 149:
      t=resource.getString("admin.header.acquisition39") ;
      break;
      case 150:
      t=resource.getString("admin.header.acquisition40");
      break;
      case 151:
      t=resource.getString("admin.header.acquisition41");
      break;
      case 152:
      t=resource.getString("admin.header.acquisition42") ;
      break;
      case 153:
      t=resource.getString("admin.header.acquisition43") ;
      break;
      case 154:
      t=resource.getString("admin.header.acquisition44") ;
      break;
      case 155:
      t=resource.getString("admin.header.acquisition45") ;
      break;
      case 156:
      t=resource.getString("admin.header.acquisition46") ;
      break;

      case 157:
      t=resource.getString("admin.header.acquisition47");
      break;
      case 158:
      t=resource.getString("admin.header.acquisition48") ;
      break;
      case 159:
      t=resource.getString("admin.header.acquisition49");
      break;
      case 160:
      t=resource.getString("admin.header.acquisition50");
      break;
      case 161:
      t=resource.getString("admin.header.acquisition51") ;
      break;
      case 162:
      t=resource.getString("admin.header.acquisition52") ;
      break;
      case 163:
      t=resource.getString("admin.header.acquisition53");
      break;
      case 164:
      t=resource.getString("admin.header.acquisition54");
      break;
          case 165:
      t=resource.getString("admin.header.acquisition55") ;
      break;
      case 166:
      t=resource.getString("admin.header.acquisition56") ;
      break;
      case 167:
      t=resource.getString("admin.header.acquisition57") ;
      break;

      case 168:
      t=resource.getString("admin.header.acquisition58");
      break;
      case 169:
      t=resource.getString("admin.header.acquisition59") ;
      break;
      case 170:
      t=resource.getString("admin.header.acquisition60");
      break;
      case 171:
      t=resource.getString("admin.header.acquisition61");
      break;
      case 172:
      t=resource.getString("admin.header.acquisition62") ;
      break;
      case 173:
      t=resource.getString("admin.header.acquisition63") ;
      break;
      case 174:
      t=resource.getString("admin.header.acquisition81") ;
      break;
      case 175:
      t=resource.getString("admin.header.acquisition64") ;
      break;
       case 176:
      t=resource.getString("admin.header.acquisition65") ;
      break;

      case 177:
      t=resource.getString("admin.header.acquisition82") ;
      break;
      case 178:
      t=resource.getString("admin.header.acquisition66") ;
      break;
      case 179:
      t=resource.getString("admin.header.acquisition84");
      break;
      case 180:
      t=resource.getString("admin.header.acquisition68") ;
      break;
      case 181:
      t=resource.getString("admin.header.acquisition85");
      break;
          case 182:
      t=resource.getString("admin.header.acquisition69") ;
      break;
      case 183:
      t=resource.getString("admin.header.acquisition83") ;
      break;
      case 184:
      t=resource.getString("admin.header.acquisition70") ;
      break;
       case 200:
      t=resource.getString("admin.header.main4") ;
      break;
      case 201:
      t=resource.getString("admin.header.cataloguing28") ;
      break;
       case 202:
      t=resource.getString("admin.header.cataloguing10") ;
      break;
       case 203:
      t=resource.getString("admin.header.cataloguing11") ;
      break;
       case 204:
      t=resource.getString("admin.header.cataloguing4") ;
      break;
       case 205:
      t=resource.getString("admin.header.cataloguing5") ;
      break;
     case 206:
      t=resource.getString("admin.header.cataloguing12") ;
      break;
      case 207:
      t=resource.getString("admin.header.cataloguing1") ;
      break;
      case 208:
      t=resource.getString("admin.header.cataloguing2") ;
      break;
      case 209:
      t=resource.getString("admin.header.cataloguing3") ;
      break;
      case 210:
      t=resource.getString("admin.header.cataloguing13") ;
      break;
      case 211:
      t=resource.getString("admin.header.cataloguing14") ;
      break;
      case 212:
      t=resource.getString("admin.header.cataloguing6") ;
      break;
       case 213:
      t=resource.getString("admin.header.cataloguing7") ;
      break;
      case 214:
      t=resource.getString("admin.header.cataloguing15") ;
      break;
      case 215:
      t=resource.getString("admin.header.cataloguing8") ;
      break;
       case 216:
      t=resource.getString("admin.header.cataloguing9") ;
      break;
       case 217:
      t=resource.getString("admin.header.cataloguing37") ;
      break;
       case 218:
      t=resource.getString("admin.header.cataloguing16") ;
      break;
      case 219:
      t=resource.getString("admin.header.cataloguing29") ;
      break;
       case 220:
      t=resource.getString("admin.header.cataloguing17") ;
      break;
      case 221:
      t=resource.getString("admin.header.cataloguing18") ;
      break;
       case 222:
      t=resource.getString("admin.header.cataloguing19") ;
      break;
       case 223:
      t=resource.getString("admin.header.cataloguing20") ;
      break;
       case 224:
      t=resource.getString("admin.header.cataloguing30") ;
      break;


       case 225:
      t=resource.getString("admin.header.cataloguing31") ;
      break;
      case 226:
      t=resource.getString("admin.header.cataloguing32") ;
      break;

        case 227:
      t=resource.getString("admin.header.cataloguing38") ;
      break;
       case 228:
      t=resource.getString("admin.header.cataloguing39") ;
      break;
       case 229:
      t=resource.getString("admin.header.cataloguing33") ;
      break;


       case 230:
      t=resource.getString("admin.header.cataloguing21") ;
      break;
      case 231:
      t=resource.getString("admin.header.cataloguing22") ;
      break;
      case 232:
      t=resource.getString("admin.header.cataloguing23") ;
      break;
        case 233:
      t=resource.getString("admin.header.cataloguing24") ;
      break;
       case 234:
      t=resource.getString("admin.header.cataloguing25") ;
      break;
      case 235:
      t=resource.getString("admin.header.cataloguing26") ;
      break;
      case 236:
      t=resource.getString("admin.header.cataloguing27") ;
      break;
      case 237:
      t=resource.getString("admin.header.cataloguing34") ;
      break;
      case 238:
      t=resource.getString("admin.header.cataloguing35") ;
      break;
        case 239:
      t=resource.getString("admin.header.cataloguing36") ;
      break;
      case 300:
      t=resource.getString("admin.header.main5") ;
      break;
      case 301:
      t=resource.getString("admin.header.circulation85") ;
      break;
       case 302:
      t=resource.getString("admin.header.circulation32") ;
      break;
       case 303:
      t=resource.getString("admin.header.circulation1") ;
      break;
       case 304:
      t=resource.getString("admin.header.circulation2") ;
      break;
       case 305:
      t=resource.getString("admin.header.circulation3") ;
      break;
       case 306:
      t=resource.getString("admin.header.circulation4") ;
      break;
       case 307:
      t=resource.getString("admin.header.circulation5") ;
      break;
       case 308:
      t=resource.getString("admin.header.circulation33");
      break;
       case 309:
      t=resource.getString("admin.header.circulation6") ;
      break;
       case 310:
      t=resource.getString("admin.header.circulation7") ;
      break;
       case 311:
      t=resource.getString("admin.header.circulation8") ;
      break;
       case 312:
      t=resource.getString("admin.header.circulation9") ;
      break;
       case 313:
      t=resource.getString("admin.header.circulation10") ;
      break;
       case 314:
      t=resource.getString("admin.header.circulation34") ;
      break;
       case 315:
      t=resource.getString("admin.header.circulation35") ;
      break;
       case 316:
      t=resource.getString("admin.header.circulation36") ;
      break;
       case 317:
      t=resource.getString("admin.header.circulation37") ;
      break;
       case 318:
      t=resource.getString("admin.header.circulation38") ;
      break;
       case 319:
      t=resource.getString("admin.header.circulation39") ;
      break;
       case 320:
      t=resource.getString("admin.header.circulation40") ;
      break;
       case 321:
      t=resource.getString("admin.header.circulation41") ;
      break;
       case 322:
      t=resource.getString("admin.header.circulation11") ;
      break;
       case 323:
      t=resource.getString("admin.header.circulation12") ;
      break;
       case 324:
      t=resource.getString("admin.header.circulation13") ;
      break;
       case 325:
      t=resource.getString("admin.header.circulation14") ;
      break;
       case 326:
      t=resource.getString("admin.header.circulation42") ;
      break;
       case 327:
      t=resource.getString("admin.header.circulation86") ;
      break;
       case 328:
      t=resource.getString("admin.header.circulation87") ;
      break;
       case 329:
      t=resource.getString("admin.header.circulation43") ;
      break;
       case 330:
      t=resource.getString("admin.header.circulation44") ;
      break;
       case 331:
      t=resource.getString("admin.header.circulation45");
      break;
       case 332:
      t=resource.getString("admin.header.circulation46") ;
      break;
       case 333:
      t=resource.getString("admin.header.circulation88") ;
      break;
       case 334:
      t=resource.getString("admin.header.circulation89") ;
      break;
       case 335:
      t=resource.getString("admin.header.circulation90") ;
      break;
       case 336:
      t=resource.getString("admin.header.circulation47") ;
      break;
      case 337:
      t=resource.getString("admin.header.circulation48") ;
      break;
       case 338:
      t=resource.getString("admin.header.circulation49") ;
      break;
       case 339:
      t=resource.getString("admin.header.circulation91") ;
      break;
       case 340:
      t=resource.getString("admin.header.circulation50") ;
      break;
       case 341:
      t=resource.getString("admin.header.circulation51") ;
      break;
      case 342:
      t=resource.getString("admin.header.circulation52") ;
      break;
       case 343:
      t=resource.getString("admin.header.circulation53") ;
      break;
       case 344:
      t=resource.getString("admin.header.circulation54") ;
      break;
  
       case 345:
      t=resource.getString("admin.header.circulation92") ;
      break;
       case 346:
      t=resource.getString("admin.header.circulation55") ;
      break;
      case 347:
      t=resource.getString("admin.header.circulation56") ;
      break;
       case 348:
      t=resource.getString("admin.header.circulation57") ;
      break;
       case 349:
      t=resource.getString("admin.header.circulation58") ;
      break;
       case 350:
      t=resource.getString("admin.header.circulation59") ;
      break;
       case 351:
      t=resource.getString("admin.header.circulation93") ;
      break;
      case 352:
      t=resource.getString("admin.header.circulation60") ;
      break;
       case 353:
      t=resource.getString("admin.header.circulation61") ;
      break;
       case 354:
      t=resource.getString("admin.header.circulation62") ;
      break;
      case 355:
      t=resource.getString("admin.header.circulation94") ;
      break;
       case 356:
      t=resource.getString("admin.header.circulation63") ;
      break;
       case 357:
      t=resource.getString("admin.header.circulation64") ;
      break;
       case 358:
      t=resource.getString("admin.header.circulation65") ;
      break;
       case 359:
      t=resource.getString("admin.header.circulation66") ;
      break;
       case 360:
      t=resource.getString("admin.header.circulation57") ;
      break;
      case 361:
      t=resource.getString("admin.header.circulation95") ;
      break;
       case 362:
      t=resource.getString("admin.header.circulation68") ;
      break;
       case 363:
      t=resource.getString("admin.header.circulation69") ;
      break;
       case 364:
      t=resource.getString("admin.header.circulation70") ;
      break;
       case 365:
      t=resource.getString("admin.header.circulation15");
      break;
      case 366:
      t=resource.getString("admin.header.circulation16") ;
      break;
       case 367:
      t=resource.getString("admin.header.circulation17") ;
      break;
       case 368:
      t=resource.getString("admin.header.circulation71") ;
      break;
       case 369:
      t=resource.getString("admin.header.circulation72") ;
      break;
      case 370:
      t=resource.getString("admin.header.circulation96") ;
      break;
      case 371:
      t=resource.getString("admin.header.circulation73") ;
      break;
       case 372:
      t=resource.getString("admin.header.circulation18") ;
      break;
       case 373:
      t=resource.getString("admin.header.circulation19") ;
      break;
       case 374:
      t=resource.getString("admin.header.circulation20") ;
      break;
       case 375:
      t=resource.getString("admin.header.circulation74");
      break;
      case 376:
      t=resource.getString("admin.header.circulation75") ;
      break;
       case 377:
      t=resource.getString("admin.header.circulation76") ;
      break;
       case 378:
      t=resource.getString("admin.header.circulation77") ;
      break;
       case 379:
      t=resource.getString("admin.header.circulation78") ;
      break;
      case 380:
      t=resource.getString("admin.header.circulation97");
      break;
      case 381:
      t=resource.getString("admin.header.circulation79") ;
      break;
       case 382:
      t=resource.getString("admin.header.circulation80") ;
      break;
       case 383:
      t=resource.getString("admin.header.circulation21") ;
      break;
       case 384:
      t=resource.getString("admin.header.circulation22") ;
      break;
       case 385:
      t=resource.getString("admin.header.circulation98");
      break;
      case 386:
      t=resource.getString("admin.header.circulation81") ;
      break;
       case 387:
      t=resource.getString("admin.header.circulation23") ;
      break;
       case 388:
      t=resource.getString("admin.header.circulation24") ;
      break;
       case 389:
      t=resource.getString("admin.header.circulation25") ;
      break;
      case 390:
      t=resource.getString("admin.header.circulation82");
      break;
      case 391:
      t=resource.getString("admin.header.circulation26") ;
      break;
       case 392:
      t=resource.getString("admin.header.circulation27") ;
      break;
       case 393:
      t=resource.getString("admin.header.circulation83") ;
      break;
       case 394:
      t=resource.getString("admin.header.circulation28") ;
      break;
       case 395:
      t=resource.getString("admin.header.circulation29") ;
      break;
      case 396:
      t=resource.getString("admin.header.circulation84") ;
      break;
       case 397:
      t=resource.getString("admin.header.circulation30") ;
      break;
       case 398:
      t=resource.getString("admin.header.circulation31") ;
      break;
       case 399:
      t=resource.getString("admin.header.circulation99") ;
      break;
      case 400:
      t=resource.getString("admin.header.main6") ;
      break;
       case 401:
      t=resource.getString("admin.header.serial70") ;
      break;
       case 402:
      t=resource.getString("admin.header.serial33") ;
      break;
       case 403:
      t=resource.getString("admin.header.serial34") ;
      break;
       case 404:
      t=resource.getString("admin.header.serial35") ;
      break;
       case 405:
      t=resource.getString("admin.header.serial36") ;
      break;
       case 406:
      t=resource.getString("admin.header.serial37") ;
      break;
       case 407:
      t=resource.getString("admin.header.serial71") ;
      break;
       case 408:
      t=resource.getString("admin.header.serial39") ;
      break;
         case 409:
      t=resource.getString("admin.header.serial40") ;
      break;
       case 410:
      t=resource.getString("admin.header.serial41") ;
      break;
       case 411:
      t=resource.getString("admin.header.serial2") ;
      break;
       case 412:
      t=resource.getString("admin.header.serial3") ;
      break;
       case 413:
      t=resource.getString("admin.header.serial42") ;
      break;
       case 414:
      t=resource.getString("admin.header.serial5") ;
      break;
       case 415:
      t=resource.getString("admin.header.serial6") ;
      break;
       case 416:
      t=resource.getString("admin.header.serial43") ;
      break;
       case 417:
      t=resource.getString("admin.header.serial72") ;
      break;
         case 418:
      t=resource.getString("admin.header.serial45") ;
      break;
       case 419:
      t=resource.getString("admin.header.serial8") ;
      break;
       case 420:
      t=resource.getString("admin.header.serial3") ;
      break;
       case 421:
      t=resource.getString("admin.header.serial46") ;
      break;
       case 422:
      t=resource.getString("admin.header.serial47") ;
      break;
       case 423:
      t=resource.getString("admin.header.serial48") ;
      break;
       case 424:
      t=resource.getString("admin.header.serial49") ;
      break;
       case 425:
      t=resource.getString("admin.header.serial50") ;
      break;
       case 426:
      t=resource.getString("admin.header.serial51") ;
      break;
         case 427:
      t=resource.getString("admin.header.serial73") ;
      break;
       case 428:
      t=resource.getString("admin.header.serial74") ;
      break;
       case 429:
      t=resource.getString("admin.header.serial53") ;
      break;
       case 430:
      t=resource.getString("admin.header.serial54") ;
      break;
       case 431:
      t=resource.getString("admin.header.serial75") ;
      break;
       case 432:
      t=resource.getString("admin.header.serial76") ;
      break;
       case 433:
      t=resource.getString("admin.header.serial56") ;
      break;
       case 434:
      t=resource.getString("admin.header.serial57") ;
      break;
       case 435:
      t=resource.getString("admin.header.serial58") ;
      break;
         case 436:
      t=resource.getString("admin.header.serial59") ;
      break;
       case 437:
      t=resource.getString("admin.header.serial77") ;
      break;
       case 438:
      t=resource.getString("admin.header.serial61") ;
      break;
       case 439:
      t=resource.getString("admin.header.serial10") ;
      break;
       case 440:
      t=resource.getString("admin.header.serial11") ;
      break;
       case 441:
      t=resource.getString("admin.header.serial12") ;
      break;
       case 442:
      t=resource.getString("admin.header.serial13") ;
      break;
         case 443:
      t=resource.getString("admin.header.serial14") ;
      break;
       case 444:
      t=resource.getString("admin.header.serial15") ;
      break;
       case 445:
      t=resource.getString("admin.header.serial62") ;
      break;
       case 446:
      t=resource.getString("admin.header.serial17") ;
      break;
       case 447:
      t=resource.getString("admin.header.serial18") ;
      break;
       case 448:
      t=resource.getString("admin.header.serial19") ;
      break;
       case 449:
      t=resource.getString("admin.header.serial78") ;
      break;
       case 450:
      t=resource.getString("admin.header.serial64") ;
      break;
       case 451:
      t=resource.getString("admin.header.serial21") ;
      break;
         case 452:
      t=resource.getString("admin.header.serial22") ;
      break;
       case 453:
      t=resource.getString("admin.header.serial23") ;
      break;
        case 454:
      t=resource.getString("admin.header.serial65") ;
      break;
       case 455:
      t=resource.getString("admin.header.serial66") ;
      break;
       case 456:
      t=resource.getString("admin.header.serial67") ;
      break;
       case 457:
      t=resource.getString("admin.header.serial25") ;
      break;
       case 458:
      t=resource.getString("admin.header.serial26") ;
      break;
       case 459:
      t=resource.getString("admin.header.serial27") ;
      break;
       case 460:
      t=resource.getString("admin.header.serial68") ;
      break;
         case 461:
      t=resource.getString("admin.header.serial29") ;
      break;
       case 462:
      t=resource.getString("admin.header.serial30") ;
      break;

     case 463:
      t=resource.getString("admin.header.serial31") ;
      break;


}
return t;
}


}
