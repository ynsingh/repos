/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.MyConnection;
import com.myapp.struts.MyQueryResult;
import java.sql.*;

/**
 *
 * @author Dushyant
 */
public class Privilege {
    static Connection con;
  static  String  sql;
   static PreparedStatement stmt;
  
  public static void   assignAdminPrivilege(String staff_id,String library_id)
  {
      try{
   con=MyConnection.getMyConnection();
   sql=("INSERT INTO privilege VALUES ('"+staff_id+"','" +library_id+"','"+false+ "','" +false+ "','"
        +false+ "','" +false+ "','" +false+ "','" +false+"','"+false+"','"+false+"')");
        stmt=con.prepareStatement(sql);
        stmt.executeUpdate();
        System.out.println("Privilege Table Populated");
        String sql1 = ("INSERT INTO acq_privilege (staff_id,library_id,acq_101, acq_102, acq_103, acq_104, acq_105, acq_106, acq_107, acq_108, acq_109, acq_110, acq_111, acq_112, acq_113, acq_114, acq_115, acq_116, acq_117, acq_118, acq_119, acq_120, acq_121, acq_122, acq_123, acq_124, acq_125, acq_126, acq_127, acq_128, acq_129, acq_130, acq_131, acq_132, acq_133, acq_134, acq_135, acq_136, acq_137, acq_138, acq_139, acq_140, acq_141, acq_142, acq_143, acq_144, acq_145, acq_146, acq_147, acq_148, acq_149, acq_150, acq_151, acq_152, acq_153, acq_154, acq_155, acq_156, acq_157, acq_158, acq_159, acq_160, acq_161, acq_162, acq_163, acq_164, acq_165, acq_166, acq_167, acq_168, acq_169, acq_170, acq_171, acq_172, acq_173, acq_174, acq_175, acq_176, acq_177, acq_178, acq_179, acq_180, acq_181, acq_182, acq_183, acq_184, acq_185, acq_186, acq_187, acq_188, acq_189, acq_190, acq_191, acq_192, acq_193, acq_194, acq_195, acq_196, acq_197, acq_198, acq_199)"+
	"VALUES ('"+staff_id+"','"+library_id+"','false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false')");

        stmt=con.prepareStatement(sql1);
        stmt.executeUpdate();
        System.out.println(" Acq Privilege Table Populated");
        String sql2 = ("INSERT INTO cat_privilege (staff_id,library_id,cat_201, cat_202, cat_203, cat_204, cat_205, cat_206, cat_207, cat_208, cat_209, cat_210, cat_211, cat_212, cat_213, cat_214, cat_215, cat_216, cat_217, cat_218, cat_219, cat_220, cat_221, cat_222, cat_223, cat_224, cat_225, cat_226, cat_227, cat_228, cat_229, cat_230, cat_231, cat_232, cat_233, cat_234, cat_235, cat_236, cat_237, cat_238, cat_239, cat_240, cat_241, cat_242, cat_243, cat_244, cat_245, cat_246, cat_247, cat_248, cat_249, cat_250, cat_251, cat_252, cat_253, cat_254, cat_255, cat_256, cat_257, cat_258, cat_259, cat_260, cat_261, cat_262, cat_263, cat_264, cat_265, cat_266, cat_267, cat_268, cat_269, cat_270, cat_271, cat_272, cat_273, cat_274, cat_275, cat_276, cat_277, cat_278, cat_279, cat_280, cat_281, cat_282, cat_283, cat_284, cat_285, cat_286, cat_287, cat_288, cat_289, cat_290, cat_291, cat_292, cat_293, cat_294, cat_295, cat_296, cat_297, cat_298, cat_299)"+
	" VALUES ('"+staff_id+"','"+library_id+"','false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false')");

        stmt=con.prepareStatement(sql2);
        stmt.executeUpdate();
        System.out.println(" Cat Privilege Table Populated");
        String sql3 = ("INSERT INTO cir_privilege (staff_id,library_id,cir_301, cir_302, cir_303, cir_304, cir_305, cir_306, cir_307, cir_308, cir_309, cir_310, cir_311, cir_312, cir_313, cir_314, cir_315, cir_316, cir_317, cir_318, cir_319, cir_320, cir_321, cir_322, cir_323, cir_324, cir_325, cir_326, cir_327, cir_328, cir_329, cir_330, cir_331, cir_332, cir_333, cir_334, cir_335, cir_336, cir_337, cir_338, cir_339, cir_340, cir_341, cir_342, cir_343, cir_344, cir_345, cir_346, cir_347, cir_348, cir_349, cir_350, cir_351, cir_352, cir_353, cir_354, cir_355, cir_356, cir_357, cir_358, cir_359, cir_360, cir_361, cir_362, cir_363, cir_364, cir_365, cir_366, cir_367, cir_368, cir_369, cir_370, cir_371, cir_372, cir_373, cir_374, cir_375, cir_376, cir_377, cir_378, cir_379, cir_380, cir_381, cir_382, cir_383, cir_384, cir_385, cir_386, cir_387, cir_388, cir_389, cir_390, cir_391, cir_392, cir_393, cir_394, cir_395, cir_396, cir_397, cir_398, cir_399)"+
	"VALUES ('"+staff_id+"','"+library_id+"','false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false')");
        stmt=con.prepareStatement(sql3);
        stmt.executeUpdate();
        System.out.println("Cir Privilege Table Populated");
        String sql4 = ("INSERT INTO ser_privilege (staff_id,library_id,ser_401, ser_402, ser_403, ser_404, ser_405, ser_406, ser_407, ser_408, ser_409, ser_410, ser_411, ser_412, ser_413, ser_414, ser_415, ser_416,ser_417, ser_418, ser_419, ser_420, ser_421, ser_422, ser_423, ser_424, ser_425, ser_426, ser_427, ser_428, ser_429, ser_430, ser_431, ser_432, ser_433, ser_434, ser_435, ser_436, ser_437, ser_438, ser_439, ser_440, ser_441, ser_442, ser_443, ser_444, ser_445, ser_446, ser_447, ser_448, ser_449, ser_450, ser_451, ser_452, ser_453, ser_454, ser_455, ser_456, ser_457, ser_458, ser_459, ser_460, ser_461, ser_462, ser_463, ser_464, ser_465, ser_466, ser_467, ser_468, ser_469, ser_470, ser_471, ser_472, ser_473, ser_474, ser_475, ser_476, ser_477, ser_478, ser_479, ser_480, ser_481, ser_482, " + "ser_483, ser_484, ser_485, ser_486, ser_487, ser_488, ser_489, ser_490, ser_491, ser_492, ser_493, ser_494, ser_495, ser_496, ser_497, ser_498, ser_499)"+
	"VALUES ('"+staff_id+"','"+library_id+"','false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false','false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false', 'false')");
        stmt=con.prepareStatement(sql4);
        stmt.executeUpdate();

 //  System.out.println(" Ser Privilege Table Populated");

      }
      catch(Exception e)
      {
      System.out.println(e);
      }
  }
  static public void assignStaffPrivilege(String staff_id,String library_id)
  {
      try
      {

          con=MyConnection.getMyConnection();

        sql=("INSERT INTO privilege VALUES ('"+staff_id+"','" +library_id+"','"+true+ "','" +true+ "','"
        +true+ "','" +true+ "','" +true+ "','" +true+"','"+true+"','"+false+"')");
        stmt=con.prepareStatement(sql);
        stmt.executeUpdate();
        System.out.println("Privilege Table Populated");
        String sql1 = ("INSERT INTO acq_privilege (staff_id,library_id,acq_101, acq_102, acq_103, acq_104, acq_105, acq_106, acq_107, acq_108, acq_109, acq_110, acq_111, acq_112, acq_113, acq_114, acq_115, acq_116, acq_117, acq_118, acq_119, acq_120, acq_121, acq_122, acq_123, acq_124, acq_125, acq_126, acq_127, acq_128, acq_129, acq_130, acq_131, acq_132, acq_133, acq_134, acq_135, acq_136, acq_137, acq_138, acq_139, acq_140, acq_141, acq_142, acq_143, acq_144, acq_145, acq_146, acq_147, acq_148, acq_149, acq_150, acq_151, acq_152, acq_153, acq_154, acq_155, acq_156, acq_157, acq_158, acq_159, acq_160, acq_161, acq_162, acq_163, acq_164, acq_165, acq_166, acq_167, acq_168, acq_169, acq_170, acq_171, acq_172, acq_173, acq_174, acq_175, acq_176, acq_177, acq_178, acq_179, acq_180, acq_181, acq_182, acq_183, acq_184, acq_185, acq_186, acq_187, acq_188, acq_189, acq_190, acq_191, acq_192, acq_193, acq_194, acq_195, acq_196, acq_197, acq_198, acq_199)"+
	"VALUES ('"+staff_id+"','"+library_id+"','true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true')");

        stmt=con.prepareStatement(sql1);
        stmt.executeUpdate();
        System.out.println(" Acq Privilege Table Populated");
        String sql2 = ("INSERT INTO cat_privilege (staff_id,library_id,cat_201, cat_202, cat_203, cat_204, cat_205, cat_206, cat_207, cat_208, cat_209, cat_210, cat_211, cat_212, cat_213, cat_214, cat_215, cat_216, cat_217, cat_218, cat_219, cat_220, cat_221, cat_222, cat_223, cat_224, cat_225, cat_226, cat_227, cat_228, cat_229, cat_230, cat_231, cat_232, cat_233, cat_234, cat_235, cat_236, cat_237, cat_238, cat_239, cat_240, cat_241, cat_242, cat_243, cat_244, cat_245, cat_246, cat_247, cat_248, cat_249, cat_250, cat_251, cat_252, cat_253, cat_254, cat_255, cat_256, cat_257, cat_258, cat_259, cat_260, cat_261, cat_262, cat_263, cat_264, cat_265, cat_266, cat_267, cat_268, cat_269, cat_270, cat_271, cat_272, cat_273, cat_274, cat_275, cat_276, cat_277, cat_278, cat_279, cat_280, cat_281, cat_282, cat_283, cat_284, cat_285, cat_286, cat_287, cat_288, cat_289, cat_290, cat_291, cat_292, cat_293, cat_294, cat_295, cat_296, cat_297, cat_298, cat_299)"+
	" VALUES ('"+staff_id+"','"+library_id+"','true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true')");

        stmt=con.prepareStatement(sql2);
        stmt.executeUpdate();
        System.out.println(" Cat Privilege Table Populated");
        String sql3 = ("INSERT INTO libms.cir_privilege (staff_id,library_id,cir_301, cir_302, cir_303, cir_304, cir_305, cir_306, cir_307, cir_308, cir_309, cir_310, cir_311, cir_312, cir_313, cir_314, cir_315, cir_316, cir_317, cir_318, cir_319, cir_320, cir_321, cir_322, cir_323, cir_324, cir_325, cir_326, cir_327, cir_328, cir_329, cir_330, cir_331, cir_332, cir_333, cir_334, cir_335, cir_336, cir_337, cir_338, cir_339, cir_340, cir_341, cir_342, cir_343, cir_344, cir_345, cir_346, cir_347, cir_348, cir_349, cir_350, cir_351, cir_352, cir_353, cir_354, cir_355, cir_356, cir_357, cir_358, cir_359, cir_360, cir_361, cir_362, cir_363, cir_364, cir_365, cir_366, cir_367, cir_368, cir_369, cir_370, cir_371, cir_372, cir_373, cir_374, cir_375, cir_376, cir_377, cir_378, cir_379, cir_380, cir_381, cir_382, cir_383, cir_384, cir_385, cir_386, cir_387, cir_388, cir_389, cir_390, cir_391, cir_392, cir_393, cir_394, cir_395, cir_396, cir_397, cir_398, cir_399)"+
	"VALUES ('"+staff_id+"','"+library_id+"','true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true')");
        stmt=con.prepareStatement(sql3);
        stmt.executeUpdate();
        System.out.println("Cir Privilege Table Populated");
        String sql4 = ("INSERT INTO libms.ser_privilege (staff_id,library_id,ser_401, ser_402, ser_403, ser_404, ser_405, ser_406, ser_407, ser_408, ser_409, ser_410, ser_411, ser_412, ser_413, ser_414, ser_415, ser_416,ser_417, ser_418, ser_419, ser_420, ser_421, ser_422, ser_423, ser_424, ser_425, ser_426, ser_427, ser_428, ser_429, ser_430, ser_431, ser_432, ser_433, ser_434, ser_435, ser_436, ser_437, ser_438, ser_439, ser_440, ser_441, ser_442, ser_443, ser_444, ser_445, ser_446, ser_447, ser_448, ser_449, ser_450, ser_451, ser_452, ser_453, ser_454, ser_455, ser_456, ser_457, ser_458, ser_459, ser_460, ser_461, ser_462, ser_463, ser_464, ser_465, ser_466, ser_467, ser_468, ser_469, ser_470, ser_471, ser_472, ser_473, ser_474, ser_475, ser_476, ser_477, ser_478, ser_479, ser_480, ser_481, ser_482, " + "ser_483, ser_484, ser_485, ser_486, ser_487, ser_488, ser_489, ser_490, ser_491, ser_492, ser_493, ser_494, ser_495, ser_496, ser_497, ser_498, ser_499)"+
	"VALUES ('"+staff_id+"','"+library_id+"','true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true','true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true', 'true')");
        stmt=con.prepareStatement(sql4);
        stmt.executeUpdate();

      }
      catch(Exception e1)
      {

      }
  }


  //privilege

  String[] substring;
String sql1,sql2,sql3,sql4,sql5;
int [] privilege_index;
/* delimiter */
String delimiter = ",";
String [] privilege={"true","true","true","true"};
String[] acq_privilege=new String[100];
String[] cat_privilege=new String[100];
String[] cir_privilege=new String[100];
String[] ser_privilege=new String[100];



public void AssignPrivilege(String string,String staff_id,String library_id)
{

for( int k=0;k<100;k++)
    {
       acq_privilege[k]="true";
       cat_privilege[k]="true";
        cir_privilege[k]="true";
        ser_privilege[k]="true";
    }
    substring = string.split(delimiter);
    

    privilege_index=new int[substring.length];
    

for(int i =0; i < substring.length; i++)
    {
    privilege_index[i]=Integer.parseInt(substring[i]);
    
    if(privilege_index[i]<5)
    {
    privilege[privilege_index[i]-1]="false";
    }

    if(privilege_index[i]>=100&&privilege_index[i]<200)
    {
    acq_privilege[privilege_index[i]-100]="false";
    }

    if(privilege_index[i]>=200&&privilege_index[i]<300)
    {
    cat_privilege[privilege_index[i]-200]="false";
    }

    if(privilege_index[i]>=300&&privilege_index[i]<400)
    {
    cir_privilege[privilege_index[i]-300]="false";
    }

    if(privilege_index[i]>=400&&privilege_index[i]<500)
    {
    ser_privilege[privilege_index[i]-400]="false";
    }
    }
    sql1="update privilege set acquisition='"+acq_privilege[0]+"',cataloguing='"+cat_privilege[0]+
         "',circulation='"+cir_privilege[0]+"',serial='"+ser_privilege[0]+"',administrator='"+privilege[0]+
         "',system_setup='"+privilege[1]+"',utilities='"+privilege[2]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    sql2="update acq_privilege set acq_101='"+acq_privilege[1]+"',acq_102='"+acq_privilege[2]+
         "',acq_103='"+acq_privilege[3]+"',acq_104='"+acq_privilege[4]+"',acq_105='"+acq_privilege[5]+
         "',acq_106='"+acq_privilege[6]+"',acq_107='"+acq_privilege[7]+"',acq_108='"+acq_privilege[8]+
         "',acq_109='"+acq_privilege[9]+"',acq_110='"+acq_privilege[10]+"',acq_111='"+acq_privilege[11]+
         "',acq_112='"+acq_privilege[12]+"',acq_113='"+acq_privilege[13]+"',acq_114='"+acq_privilege[14]+
         "',acq_115='"+acq_privilege[15]+"',acq_116='"+acq_privilege[16]+"',acq_117='"+acq_privilege[17]+
         "',acq_118='"+acq_privilege[18]+"',acq_119='"+acq_privilege[19]+"',acq_120='"+acq_privilege[20]+
         "',acq_121='"+acq_privilege[21]+"',acq_122='"+acq_privilege[22]+"',acq_123='"+acq_privilege[23]+
         "',acq_124='"+acq_privilege[24]+"',acq_125='"+acq_privilege[25]+"',acq_126='"+acq_privilege[26]+
         "',acq_127='"+acq_privilege[27]+"',acq_128='"+acq_privilege[28]+"',acq_129='"+acq_privilege[29]+
         "',acq_130='"+acq_privilege[30]+"',acq_131='"+acq_privilege[31]+"',acq_132='"+acq_privilege[32]+
         "',acq_133='"+acq_privilege[33]+"',acq_134='"+acq_privilege[34]+"',acq_135='"+acq_privilege[35]+
         "',acq_136='"+acq_privilege[36]+"',acq_137='"+acq_privilege[37]+"',acq_138='"+acq_privilege[38]+
         "',acq_139='"+acq_privilege[39]+"',acq_140='"+acq_privilege[40]+"',acq_141='"+acq_privilege[41]+
         "',acq_142='"+acq_privilege[42]+"',acq_143='"+acq_privilege[43]+"',acq_144='"+acq_privilege[44]+
         "',acq_145='"+acq_privilege[45]+"',acq_146='"+acq_privilege[46]+"',acq_147='"+acq_privilege[47]+
         "',acq_148='"+acq_privilege[48]+"',acq_149='"+acq_privilege[49]+"',acq_150='"+acq_privilege[50]+
         "',acq_151='"+acq_privilege[51]+"',acq_152='"+acq_privilege[52]+"',acq_153='"+acq_privilege[53]+
         "',acq_154='"+acq_privilege[54]+"',acq_155='"+acq_privilege[55]+"',acq_156='"+acq_privilege[56]+
         "',acq_157='"+acq_privilege[57]+"',acq_158='"+acq_privilege[58]+"',acq_159='"+acq_privilege[59]+
         "',acq_160='"+acq_privilege[60]+"',acq_161='"+acq_privilege[61]+"',acq_162='"+acq_privilege[62]+
         "',acq_163='"+acq_privilege[63]+"',acq_164='"+acq_privilege[64]+"',acq_165='"+acq_privilege[65]+
         "',acq_166='"+acq_privilege[66]+"',acq_167='"+acq_privilege[67]+"',acq_168='"+acq_privilege[68]+
         "',acq_169='"+acq_privilege[69]+"',acq_170='"+acq_privilege[70]+"',acq_171='"+acq_privilege[71]+
         "',acq_172='"+acq_privilege[72]+"',acq_173='"+acq_privilege[73]+"',acq_174='"+acq_privilege[74]+
         "',acq_175='"+acq_privilege[75]+"',acq_176='"+acq_privilege[76]+"',acq_177='"+acq_privilege[77]+
         "',acq_178='"+acq_privilege[78]+"',acq_179='"+acq_privilege[79]+"',acq_180='"+acq_privilege[80]+
         "',acq_181='"+acq_privilege[81]+"',acq_182='"+acq_privilege[82]+"',acq_183='"+acq_privilege[83]+
         "',acq_184='"+acq_privilege[84]+"',acq_185='"+acq_privilege[85]+"',acq_186='"+acq_privilege[86]+
         "',acq_187='"+acq_privilege[87]+"',acq_188='"+acq_privilege[88]+
         "',acq_189='"+acq_privilege[89]+"',acq_190='"+acq_privilege[90]+"',acq_191='"+acq_privilege[91]+
         "',acq_192='"+acq_privilege[92]+"',acq_193='"+acq_privilege[93]+"',acq_194='"+acq_privilege[94]+
         "',acq_195='"+acq_privilege[95]+"',acq_196='"+acq_privilege[96]+"',acq_197='"+acq_privilege[97]+
         "',acq_198='"+acq_privilege[98]+"',acq_199='"+acq_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

           sql3="update cat_privilege set cat_201='"+cat_privilege[1]+"',cat_202='"+cat_privilege[2]+
         "',cat_203='"+cat_privilege[3]+"',cat_204='"+cat_privilege[4]+"',cat_205='"+cat_privilege[5]+
         "',cat_206='"+cat_privilege[6]+"',cat_207='"+cat_privilege[7]+"',cat_208='"+cat_privilege[8]+
         "',cat_209='"+cat_privilege[9]+"',cat_210='"+cat_privilege[10]+"',cat_211='"+cat_privilege[11]+
         "',cat_212='"+cat_privilege[12]+"',cat_213='"+cat_privilege[13]+"',cat_214='"+cat_privilege[14]+
         "',cat_215='"+cat_privilege[15]+"',cat_216='"+cat_privilege[16]+"',cat_217='"+cat_privilege[17]+
         "',cat_218='"+cat_privilege[18]+"',cat_219='"+cat_privilege[19]+"',cat_220='"+cat_privilege[20]+
         "',cat_221='"+cat_privilege[21]+"',cat_222='"+cat_privilege[22]+"',cat_223='"+cat_privilege[23]+
         "',cat_224='"+cat_privilege[24]+"',cat_225='"+cat_privilege[25]+"',cat_226='"+cat_privilege[26]+
         "',cat_227='"+cat_privilege[27]+"',cat_228='"+cat_privilege[28]+"',cat_229='"+cat_privilege[29]+
         "',cat_230='"+cat_privilege[30]+"',cat_231='"+cat_privilege[31]+"',cat_232='"+cat_privilege[32]+
         "',cat_233='"+cat_privilege[33]+"',cat_234='"+cat_privilege[34]+"',cat_235='"+cat_privilege[35]+
         "',cat_236='"+cat_privilege[36]+"',cat_237='"+cat_privilege[37]+"',cat_238='"+cat_privilege[38]+
         "',cat_239='"+cat_privilege[39]+"',cat_240='"+cat_privilege[40]+"',cat_241='"+cat_privilege[41]+
         "',cat_242='"+cat_privilege[42]+"',cat_243='"+cat_privilege[43]+"',cat_244='"+cat_privilege[44]+
         "',cat_245='"+cat_privilege[45]+"',cat_246='"+cat_privilege[46]+"',cat_247='"+cat_privilege[47]+
         "',cat_248='"+cat_privilege[48]+"',cat_249='"+cat_privilege[49]+"',cat_250='"+cat_privilege[50]+
         "',cat_251='"+cat_privilege[51]+"',cat_252='"+cat_privilege[52]+"',cat_253='"+cat_privilege[53]+
         "',cat_254='"+cat_privilege[54]+"',cat_255='"+cat_privilege[55]+"',cat_256='"+cat_privilege[56]+
         "',cat_257='"+cat_privilege[57]+"',cat_258='"+cat_privilege[58]+"',cat_259='"+cat_privilege[59]+
         "',cat_260='"+cat_privilege[60]+"',cat_261='"+cat_privilege[61]+"',cat_262='"+cat_privilege[62]+
         "',cat_263='"+cat_privilege[63]+"',cat_264='"+cat_privilege[64]+"',cat_265='"+cat_privilege[65]+
         "',cat_266='"+cat_privilege[66]+"',cat_267='"+cat_privilege[67]+"',cat_268='"+cat_privilege[68]+
         "',cat_269='"+cat_privilege[69]+"',cat_270='"+cat_privilege[70]+"',cat_271='"+cat_privilege[71]+
         "',cat_272='"+cat_privilege[72]+"',cat_273='"+cat_privilege[73]+"',cat_274='"+cat_privilege[74]+
         "',cat_275='"+cat_privilege[75]+"',cat_276='"+cat_privilege[76]+"',cat_277='"+cat_privilege[77]+
         "',cat_278='"+cat_privilege[78]+"',cat_279='"+cat_privilege[79]+"',cat_280='"+cat_privilege[80]+
         "',cat_281='"+cat_privilege[81]+"',cat_282='"+cat_privilege[82]+"',cat_283='"+cat_privilege[83]+
         "',cat_284='"+cat_privilege[84]+"',cat_285='"+cat_privilege[85]+"',cat_286='"+cat_privilege[86]+
         "',cat_287='"+cat_privilege[87]+"',cat_288='"+cat_privilege[88]+"',cat_289='"+cat_privilege[89]+
         "',cat_290='"+cat_privilege[90]+"',cat_291='"+cat_privilege[91]+
         "',cat_292='"+cat_privilege[92]+"',cat_293='"+cat_privilege[93]+"',cat_294='"+cat_privilege[94]+
         "',cat_295='"+cat_privilege[95]+"',cat_296='"+cat_privilege[96]+"',cat_297='"+cat_privilege[97]+
         "',cat_298='"+cat_privilege[98]+"',cat_299='"+cat_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    sql4="update  cir_privilege set cir_301='"+cir_privilege[1]+"',cir_302='"+cir_privilege[2]+
         "',cir_303='"+cir_privilege[3]+"',cir_304='"+cir_privilege[4]+"',cir_305='"+cir_privilege[5]+
         "',cir_306='"+cir_privilege[6]+"',cir_307='"+cir_privilege[7]+"',cir_308='"+cir_privilege[8]+
         "',cir_309='"+cir_privilege[9]+"',cir_310='"+cir_privilege[10]+"',cir_311='"+cir_privilege[11]+
         "',cir_312='"+cir_privilege[12]+"',cir_313='"+cir_privilege[13]+"',cir_314='"+cir_privilege[14]+
         "',cir_315='"+cir_privilege[15]+"',cir_316='"+cir_privilege[16]+"',cir_317='"+cir_privilege[17]+
         "',cir_318='"+cir_privilege[18]+"',cir_319='"+cir_privilege[19]+"',cir_320='"+cir_privilege[20]+
         "',cir_321='"+cir_privilege[21]+"',cir_322='"+cir_privilege[22]+"',cir_323='"+cir_privilege[23]+
         "',cir_324='"+cir_privilege[24]+"',cir_325='"+cir_privilege[25]+"',cir_326='"+cir_privilege[26]+
         "',cir_327='"+cir_privilege[27]+"',cir_328='"+cir_privilege[28]+"',cir_329='"+cir_privilege[29]+
         "',cir_330='"+cir_privilege[30]+"',cir_331='"+cir_privilege[31]+"',cir_332='"+cir_privilege[32]+
         "',cir_333='"+cir_privilege[33]+"',cir_334='"+cir_privilege[34]+"',cir_335='"+cir_privilege[35]+
         "',cir_336='"+cir_privilege[36]+"',cir_337='"+cir_privilege[37]+"',cir_338='"+cir_privilege[38]+
         "',cir_339='"+cir_privilege[39]+"',cir_340='"+cir_privilege[40]+"',cir_341='"+cir_privilege[41]+
         "',cir_342='"+cir_privilege[42]+"',cir_343='"+cir_privilege[43]+"',cir_344='"+cir_privilege[44]+
         "',cir_345='"+cir_privilege[45]+"',cir_346='"+cir_privilege[46]+"',cir_347='"+cir_privilege[47]+
         "',cir_348='"+cir_privilege[48]+"',cir_349='"+cir_privilege[49]+"',cir_350='"+cir_privilege[50]+
         "',cir_351='"+cir_privilege[51]+"',cir_352='"+cir_privilege[52]+"',cir_353='"+cir_privilege[53]+
         "',cir_354='"+cir_privilege[54]+"',cir_355='"+cir_privilege[55]+"',cir_356='"+cir_privilege[56]+
         "',cir_357='"+cir_privilege[57]+"',cir_358='"+cir_privilege[58]+"',cir_359='"+cir_privilege[59]+
         "',cir_360='"+cir_privilege[60]+"',cir_361='"+cir_privilege[61]+"',cir_362='"+cir_privilege[62]+
         "',cir_363='"+cir_privilege[63]+"',cir_364='"+cir_privilege[64]+"',cir_365='"+cir_privilege[65]+
         "',cir_366='"+cir_privilege[66]+"',cir_367='"+cir_privilege[67]+"',cir_368='"+cir_privilege[68]+
         "',cir_369='"+cir_privilege[69]+"',cir_370='"+cir_privilege[70]+"',cir_371='"+cir_privilege[71]+
         "',cir_372='"+cir_privilege[72]+"',cir_373='"+cir_privilege[73]+"',cir_374='"+cir_privilege[74]+
         "',cir_375='"+cir_privilege[75]+"',cir_376='"+cir_privilege[76]+"',cir_377='"+cir_privilege[77]+
         "',cir_378='"+cir_privilege[78]+"',cir_379='"+cir_privilege[79]+"',cir_380='"+cir_privilege[80]+
         "',cir_381='"+cir_privilege[81]+"',cir_382='"+cir_privilege[82]+"',cir_383='"+cir_privilege[83]+
         "',cir_384='"+cir_privilege[84]+"',cir_385='"+cir_privilege[85]+"',cir_386='"+cir_privilege[86]+
         "',cir_387='"+cir_privilege[87]+"',cir_388='"+cir_privilege[88]+
         "',cir_389='"+cir_privilege[89]+"',cir_390='"+cir_privilege[90]+"',cir_391='"+cir_privilege[91]+
         "',cir_392='"+cir_privilege[92]+"',cir_393='"+cir_privilege[93]+"',cir_394='"+cir_privilege[94]+
         "',cir_395='"+cir_privilege[95]+"',cir_396='"+cir_privilege[96]+"',cir_397='"+cir_privilege[97]+
         "',cir_398='"+cir_privilege[98]+"',cir_399='"+cir_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    sql5="update ser_privilege set ser_401='"+ser_privilege[1]+"',ser_402='"+ser_privilege[2]+
         "',ser_403='"+ser_privilege[3]+"',ser_404='"+ser_privilege[4]+"',ser_405='"+ser_privilege[5]+
         "',ser_406='"+ser_privilege[6]+"',ser_407='"+ser_privilege[7]+"',ser_408='"+ser_privilege[8]+
         "',ser_409='"+ser_privilege[9]+"',ser_410='"+ser_privilege[10]+"',ser_411='"+ser_privilege[11]+
         "',ser_412='"+ser_privilege[12]+"',ser_413='"+ser_privilege[13]+"',ser_414='"+ser_privilege[14]+
         "',ser_415='"+ser_privilege[15]+"',ser_416='"+ser_privilege[16]+"',ser_417='"+ser_privilege[17]+
         "',ser_418='"+ser_privilege[18]+"',ser_419='"+ser_privilege[19]+"',ser_420='"+ser_privilege[20]+
         "',ser_421='"+ser_privilege[21]+"',ser_422='"+ser_privilege[22]+"',ser_423='"+ser_privilege[23]+
         "',ser_424='"+ser_privilege[24]+"',ser_425='"+ser_privilege[25]+"',ser_426='"+ser_privilege[26]+
         "',ser_427='"+ser_privilege[27]+"',ser_428='"+ser_privilege[28]+"',ser_429='"+ser_privilege[29]+
         "',ser_430='"+ser_privilege[30]+"',ser_431='"+ser_privilege[31]+"',ser_432='"+ser_privilege[32]+
         "',ser_433='"+ser_privilege[33]+"',ser_434='"+ser_privilege[34]+"',ser_435='"+ser_privilege[35]+
         "',ser_436='"+ser_privilege[36]+"',ser_437='"+ser_privilege[37]+"',ser_438='"+ser_privilege[38]+
         "',ser_439='"+ser_privilege[39]+"',ser_440='"+ser_privilege[40]+"',ser_441='"+ser_privilege[41]+
         "',ser_442='"+ser_privilege[42]+"',ser_443='"+ser_privilege[43]+"',ser_444='"+ser_privilege[44]+
         "',ser_445='"+ser_privilege[45]+"',ser_446='"+ser_privilege[46]+"',ser_447='"+ser_privilege[47]+
         "',ser_448='"+ser_privilege[48]+"',ser_449='"+ser_privilege[49]+"',ser_450='"+ser_privilege[50]+
         "',ser_451='"+ser_privilege[51]+"',ser_452='"+ser_privilege[52]+"',ser_453='"+ser_privilege[53]+
         "',ser_454='"+ser_privilege[54]+"',ser_455='"+ser_privilege[55]+"',ser_456='"+ser_privilege[56]+
         "',ser_457='"+ser_privilege[57]+"',ser_458='"+ser_privilege[58]+"',ser_459='"+ser_privilege[59]+
         "',ser_460='"+ser_privilege[61]+"',ser_461='"+ser_privilege[61]+"',ser_462='"+ser_privilege[62]+
         "',ser_463='"+ser_privilege[63]+"',ser_464='"+ser_privilege[64]+"',ser_465='"+ser_privilege[65]+
         "',ser_466='"+ser_privilege[66]+"',ser_467='"+ser_privilege[67]+"',ser_468='"+ser_privilege[68]+
         "',ser_469='"+ser_privilege[69]+"',ser_470='"+ser_privilege[70]+"',ser_471='"+ser_privilege[71]+
         "',ser_472='"+ser_privilege[72]+"',ser_473='"+ser_privilege[73]+"',ser_474='"+ser_privilege[74]+
         "',ser_475='"+ser_privilege[75]+"',ser_476='"+ser_privilege[76]+"',ser_477='"+ser_privilege[77]+
         "',ser_478='"+ser_privilege[78]+"',ser_479='"+ser_privilege[79]+"',ser_480='"+ser_privilege[80]+
         "',ser_481='"+ser_privilege[81]+"',ser_482='"+ser_privilege[82]+"',ser_483='"+ser_privilege[83]+
         "',ser_484='"+ser_privilege[84]+"',ser_485='"+ser_privilege[85]+"',ser_486='"+ser_privilege[86]+
         "',ser_487='"+ser_privilege[87]+"',ser_488='"+ser_privilege[88]+"',ser_489='"+ser_privilege[89]+
         "',ser_490='"+ser_privilege[90]+"',ser_491='"+ser_privilege[91]+
         "',ser_492='"+ser_privilege[92]+"',ser_493='"+ser_privilege[93]+"',ser_494='"+ser_privilege[94]+
         "',ser_495='"+ser_privilege[95]+"',ser_496='"+ser_privilege[96]+"',ser_497='"+ser_privilege[97]+
         "',ser_498='"+ser_privilege[98]+"',ser_499='"+ser_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";
//System.out.println(sql1);
//System.out.println(sql2);
//System.out.println(sql3);
//System.out.println(sql4);
//System.out.println(sql5);
   MyQueryResult.getMyExecuteUpdate(sql1);
   MyQueryResult.getMyExecuteUpdate(sql2);
   MyQueryResult.getMyExecuteUpdate(sql3);
   MyQueryResult.getMyExecuteUpdate(sql4);
   MyQueryResult.getMyExecuteUpdate(sql5);
   }

  public ResultSet[] backupPrivilege(String staff_id,String library_id)
  {
 String priv_sql1,priv_sql2,priv_sql3,priv_sql4,priv_sql5;
 ResultSet pre_rst1,pre_rst2,pre_rst3,pre_rst4,pre_rst5;
 ResultSet resultset[]=new ResultSet[5];
priv_sql1="Select *  from privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";
priv_sql2="Select *  from acq_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";
priv_sql3="Select *  from cat_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";
priv_sql4="Select *  from cir_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";
priv_sql5="Select *  from ser_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'";

pre_rst1=MyQueryResult.getMyExecuteQuery(priv_sql1);
resultset[0]=pre_rst1;
pre_rst2=MyQueryResult.getMyExecuteQuery(priv_sql2);
resultset[1]=pre_rst2;
pre_rst3=MyQueryResult.getMyExecuteQuery(priv_sql3);
resultset[2]=pre_rst3;
pre_rst4=MyQueryResult.getMyExecuteQuery(priv_sql4);
resultset[3]=pre_rst4;
pre_rst5=MyQueryResult.getMyExecuteQuery(priv_sql5);
resultset[4]=pre_rst5;
try
{
pre_rst1.next();
pre_rst1.beforeFirst();
}catch(Exception e)
{
System.out.println("Error in Privilege.backupPrivilege:"+e);
}
return resultset;

  }



public void rollbackPrivilege(ResultSet resultset[], String staff_id, String library_id) 
{

ResultSet pre_rst1=resultset[0],pre_rst2=resultset[1],pre_rst3=resultset[2],pre_rst4=resultset[3],pre_rst5=resultset[4];
String pre_sql1,pre_sql2,pre_sql3,pre_sql4,pre_sql5;


 int pre_start=0;
 String pre_privilege[]=new String[10];
 String pre_acq_privilege[]=new String[100];
 String pre_cat_privilege[]=new String[100];
 String pre_cir_privilege[]=new String[100];
 String pre_ser_privilege[]=new String[100];
try{
 pre_rst1.next();
while(pre_start<8)
{
  System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"+resultset[0].getString(8)+staff_id+library_id);
  pre_privilege[pre_start]= pre_rst1.getString(pre_start+3);
  pre_start++;
}

pre_start=1;
pre_rst2.next();
pre_rst3.next();
pre_rst4.next();
pre_rst5.next();
while(pre_start<100)
{
  pre_acq_privilege[pre_start]= pre_rst2.getString(pre_start+2);
  pre_cat_privilege[pre_start]= pre_rst3.getString(pre_start+2);
  pre_cir_privilege[pre_start]= pre_rst4.getString(pre_start+2);
  pre_ser_privilege[pre_start]= pre_rst5.getString(pre_start+2);
  pre_start++;
}
}catch(Exception e)
{
System.out.println("Error in Privilege.rollbackPrivilege"+e);
}

pre_sql1="update privilege set acquisition='"+pre_privilege[0]+"',cataloguing='"+pre_privilege[1]+
         "',circulation='"+pre_privilege[2]+"',serial='"+pre_privilege[3]+"',administrator='"+pre_privilege[4]+
         "',system_setup='"+pre_privilege[5]+"',utilities='"+pre_privilege[6]+"',opac='"+pre_privilege[7]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    pre_sql2="update acq_privilege set acq_101='"+pre_acq_privilege[1]+"',acq_102='"+pre_acq_privilege[2]+
         "',acq_103='"+pre_acq_privilege[3]+"',acq_104='"+pre_acq_privilege[4]+"',acq_105='"+pre_acq_privilege[5]+
         "',acq_106='"+pre_acq_privilege[6]+"',acq_107='"+pre_acq_privilege[7]+"',acq_108='"+pre_acq_privilege[8]+
         "',acq_109='"+pre_acq_privilege[9]+"',acq_110='"+pre_acq_privilege[10]+"',acq_111='"+pre_acq_privilege[11]+
         "',acq_112='"+pre_acq_privilege[12]+"',acq_113='"+pre_acq_privilege[13]+"',acq_114='"+pre_acq_privilege[14]+
         "',acq_115='"+pre_acq_privilege[15]+"',acq_116='"+pre_acq_privilege[16]+"',acq_117='"+pre_acq_privilege[17]+
         "',acq_118='"+pre_acq_privilege[18]+"',acq_119='"+pre_acq_privilege[19]+"',acq_120='"+pre_acq_privilege[20]+
         "',acq_121='"+pre_acq_privilege[21]+"',acq_122='"+pre_acq_privilege[22]+"',acq_123='"+pre_acq_privilege[23]+
         "',acq_124='"+pre_acq_privilege[24]+"',acq_125='"+pre_acq_privilege[25]+"',acq_126='"+pre_acq_privilege[26]+
         "',acq_127='"+pre_acq_privilege[27]+"',acq_128='"+pre_acq_privilege[28]+"',acq_129='"+pre_acq_privilege[29]+
         "',acq_130='"+pre_acq_privilege[30]+"',acq_131='"+pre_acq_privilege[31]+"',acq_132='"+pre_acq_privilege[32]+
         "',acq_133='"+pre_acq_privilege[33]+"',acq_134='"+pre_acq_privilege[34]+"',acq_135='"+pre_acq_privilege[35]+
         "',acq_136='"+pre_acq_privilege[36]+"',acq_137='"+pre_acq_privilege[37]+"',acq_138='"+pre_acq_privilege[38]+
         "',acq_139='"+pre_acq_privilege[39]+"',acq_140='"+pre_acq_privilege[40]+"',acq_141='"+pre_acq_privilege[41]+
         "',acq_142='"+pre_acq_privilege[42]+"',acq_143='"+pre_acq_privilege[43]+"',acq_144='"+pre_acq_privilege[44]+
         "',acq_145='"+pre_acq_privilege[45]+"',acq_146='"+pre_acq_privilege[46]+"',acq_147='"+pre_acq_privilege[47]+
         "',acq_148='"+pre_acq_privilege[48]+"',acq_149='"+pre_acq_privilege[49]+"',acq_150='"+pre_acq_privilege[50]+
         "',acq_151='"+pre_acq_privilege[51]+"',acq_152='"+pre_acq_privilege[52]+"',acq_153='"+pre_acq_privilege[53]+
         "',acq_154='"+pre_acq_privilege[54]+"',acq_155='"+pre_acq_privilege[55]+"',acq_156='"+pre_acq_privilege[56]+
         "',acq_157='"+pre_acq_privilege[57]+"',acq_158='"+pre_acq_privilege[58]+"',acq_159='"+pre_acq_privilege[59]+
         "',acq_160='"+pre_acq_privilege[60]+"',acq_161='"+pre_acq_privilege[61]+"',acq_162='"+pre_acq_privilege[62]+
         "',acq_163='"+pre_acq_privilege[63]+"',acq_164='"+pre_acq_privilege[64]+"',acq_165='"+pre_acq_privilege[65]+
         "',acq_166='"+pre_acq_privilege[66]+"',acq_167='"+pre_acq_privilege[67]+"',acq_168='"+pre_acq_privilege[68]+
         "',acq_169='"+pre_acq_privilege[69]+"',acq_170='"+pre_acq_privilege[70]+"',acq_171='"+pre_acq_privilege[71]+
         "',acq_172='"+pre_acq_privilege[72]+"',acq_173='"+pre_acq_privilege[73]+"',acq_174='"+pre_acq_privilege[74]+
         "',acq_175='"+pre_acq_privilege[75]+"',acq_176='"+pre_acq_privilege[76]+"',acq_177='"+pre_acq_privilege[77]+
         "',acq_178='"+pre_acq_privilege[78]+"',acq_179='"+pre_acq_privilege[79]+"',acq_180='"+pre_acq_privilege[80]+
         "',acq_181='"+pre_acq_privilege[81]+"',acq_182='"+pre_acq_privilege[82]+"',acq_183='"+pre_acq_privilege[83]+
         "',acq_184='"+pre_acq_privilege[84]+"',acq_185='"+pre_acq_privilege[85]+"',acq_186='"+pre_acq_privilege[86]+
         "',acq_187='"+pre_acq_privilege[87]+"',acq_188='"+pre_acq_privilege[88]+
         "',acq_189='"+pre_acq_privilege[89]+"',acq_190='"+pre_acq_privilege[90]+"',acq_191='"+pre_acq_privilege[91]+
         "',acq_192='"+pre_acq_privilege[92]+"',acq_193='"+pre_acq_privilege[93]+"',acq_194='"+pre_acq_privilege[94]+
         "',acq_195='"+pre_acq_privilege[95]+"',acq_196='"+pre_acq_privilege[96]+"',acq_197='"+pre_acq_privilege[97]+
         "',acq_198='"+pre_acq_privilege[98]+"',acq_199='"+pre_acq_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

           pre_sql3="update cat_privilege set cat_201='"+pre_cat_privilege[1]+"',cat_202='"+pre_cat_privilege[2]+
         "',cat_203='"+pre_cat_privilege[3]+"',cat_204='"+pre_cat_privilege[4]+"',cat_205='"+pre_cat_privilege[5]+
         "',cat_206='"+pre_cat_privilege[6]+"',cat_207='"+pre_cat_privilege[7]+"',cat_208='"+pre_cat_privilege[8]+
         "',cat_209='"+pre_cat_privilege[9]+"',cat_210='"+pre_cat_privilege[10]+"',cat_211='"+pre_cat_privilege[11]+
         "',cat_212='"+pre_cat_privilege[12]+"',cat_213='"+pre_cat_privilege[13]+"',cat_214='"+pre_cat_privilege[14]+
         "',cat_215='"+pre_cat_privilege[15]+"',cat_216='"+pre_cat_privilege[16]+"',cat_217='"+pre_cat_privilege[17]+
         "',cat_218='"+pre_cat_privilege[18]+"',cat_219='"+pre_cat_privilege[19]+"',cat_220='"+pre_cat_privilege[20]+
         "',cat_221='"+pre_cat_privilege[21]+"',cat_222='"+pre_cat_privilege[22]+"',cat_223='"+pre_cat_privilege[23]+
         "',cat_224='"+pre_cat_privilege[24]+"',cat_225='"+pre_cat_privilege[25]+"',cat_226='"+pre_cat_privilege[26]+
         "',cat_227='"+pre_cat_privilege[27]+"',cat_228='"+pre_cat_privilege[28]+"',cat_229='"+pre_cat_privilege[29]+
         "',cat_230='"+pre_cat_privilege[30]+"',cat_231='"+pre_cat_privilege[31]+"',cat_232='"+pre_cat_privilege[32]+
         "',cat_233='"+pre_cat_privilege[33]+"',cat_234='"+pre_cat_privilege[34]+"',cat_235='"+pre_cat_privilege[35]+
         "',cat_236='"+pre_cat_privilege[36]+"',cat_237='"+pre_cat_privilege[37]+"',cat_238='"+pre_cat_privilege[38]+
         "',cat_239='"+pre_cat_privilege[39]+"',cat_240='"+pre_cat_privilege[40]+"',cat_241='"+pre_cat_privilege[41]+
         "',cat_242='"+pre_cat_privilege[42]+"',cat_243='"+pre_cat_privilege[43]+"',cat_244='"+pre_cat_privilege[44]+
         "',cat_245='"+pre_cat_privilege[45]+"',cat_246='"+pre_cat_privilege[46]+"',cat_247='"+pre_cat_privilege[47]+
         "',cat_248='"+pre_cat_privilege[48]+"',cat_249='"+pre_cat_privilege[49]+"',cat_250='"+pre_cat_privilege[50]+
         "',cat_251='"+pre_cat_privilege[51]+"',cat_252='"+pre_cat_privilege[52]+"',cat_253='"+pre_cat_privilege[53]+
         "',cat_254='"+pre_cat_privilege[54]+"',cat_255='"+pre_cat_privilege[55]+"',cat_256='"+pre_cat_privilege[56]+
         "',cat_257='"+pre_cat_privilege[57]+"',cat_258='"+pre_cat_privilege[58]+"',cat_259='"+pre_cat_privilege[59]+
         "',cat_260='"+pre_cat_privilege[61]+"',cat_261='"+pre_cat_privilege[61]+"',cat_262='"+pre_cat_privilege[62]+
         "',cat_263='"+pre_cat_privilege[63]+"',cat_264='"+pre_cat_privilege[64]+"',cat_265='"+pre_cat_privilege[65]+
         "',cat_266='"+pre_cat_privilege[66]+"',cat_267='"+pre_cat_privilege[67]+"',cat_268='"+pre_cat_privilege[68]+
         "',cat_269='"+pre_cat_privilege[69]+"',cat_270='"+pre_cat_privilege[70]+"',cat_271='"+pre_cat_privilege[71]+
         "',cat_272='"+pre_cat_privilege[72]+"',cat_273='"+pre_cat_privilege[73]+"',cat_274='"+pre_cat_privilege[74]+
         "',cat_275='"+pre_cat_privilege[75]+"',cat_276='"+pre_cat_privilege[76]+"',cat_277='"+pre_cat_privilege[77]+
         "',cat_278='"+pre_cat_privilege[78]+"',cat_279='"+pre_cat_privilege[79]+"',cat_280='"+pre_cat_privilege[80]+
         "',cat_281='"+pre_cat_privilege[81]+"',cat_282='"+pre_cat_privilege[82]+"',cat_283='"+pre_cat_privilege[83]+
         "',cat_284='"+pre_cat_privilege[84]+"',cat_285='"+pre_cat_privilege[85]+"',cat_286='"+pre_cat_privilege[87]+
         "',cat_287='"+pre_cat_privilege[87]+"',cat_288='"+pre_cat_privilege[88]+"',cat_289='"+pre_cat_privilege[89]+
         "',cat_290='"+pre_cat_privilege[90]+"',cat_291='"+pre_cat_privilege[91]+
         "',cat_292='"+pre_cat_privilege[92]+"',cat_293='"+pre_cat_privilege[93]+"',cat_294='"+pre_cat_privilege[94]+
         "',cat_295='"+pre_cat_privilege[95]+"',cat_296='"+pre_cat_privilege[96]+"',cat_297='"+pre_cat_privilege[97]+
         "',cat_298='"+pre_cat_privilege[98]+"',cat_299='"+pre_cat_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    pre_sql4="update  cir_privilege set cir_301='"+pre_cir_privilege[1]+"',cir_302='"+pre_cir_privilege[2]+
         "',cir_303='"+pre_cir_privilege[3]+"',cir_304='"+pre_cir_privilege[4]+"',cir_305='"+pre_cir_privilege[5]+
         "',cir_306='"+pre_cir_privilege[6]+"',cir_307='"+pre_cir_privilege[7]+"',cir_308='"+pre_cir_privilege[8]+
         "',cir_309='"+pre_cir_privilege[9]+"',cir_310='"+pre_cir_privilege[10]+"',cir_311='"+pre_cir_privilege[11]+
         "',cir_312='"+pre_cir_privilege[12]+"',cir_313='"+pre_cir_privilege[13]+"',cir_314='"+pre_cir_privilege[14]+
         "',cir_315='"+pre_cir_privilege[15]+"',cir_316='"+pre_cir_privilege[16]+"',cir_317='"+pre_cir_privilege[17]+
         "',cir_318='"+pre_cir_privilege[18]+"',cir_319='"+pre_cir_privilege[19]+"',cir_320='"+pre_cir_privilege[20]+
         "',cir_321='"+pre_cir_privilege[21]+"',cir_322='"+pre_cir_privilege[22]+"',cir_323='"+pre_cir_privilege[23]+
         "',cir_324='"+pre_cir_privilege[24]+"',cir_325='"+pre_cir_privilege[25]+"',cir_326='"+pre_cir_privilege[26]+
         "',cir_327='"+pre_cir_privilege[27]+"',cir_328='"+pre_cir_privilege[28]+"',cir_329='"+pre_cir_privilege[29]+
         "',cir_330='"+pre_cir_privilege[30]+"',cir_331='"+pre_cir_privilege[31]+"',cir_332='"+pre_cir_privilege[32]+
         "',cir_333='"+pre_cir_privilege[33]+"',cir_334='"+pre_cir_privilege[34]+"',cir_335='"+pre_cir_privilege[35]+
         "',cir_336='"+pre_cir_privilege[36]+"',cir_337='"+pre_cir_privilege[37]+"',cir_338='"+pre_cir_privilege[38]+
         "',cir_339='"+pre_cir_privilege[39]+"',cir_340='"+pre_cir_privilege[40]+"',cir_341='"+pre_cir_privilege[41]+
         "',cir_342='"+pre_cir_privilege[42]+"',cir_343='"+pre_cir_privilege[43]+"',cir_344='"+pre_cir_privilege[44]+
         "',cir_345='"+pre_cir_privilege[45]+"',cir_346='"+pre_cir_privilege[46]+"',cir_347='"+pre_cir_privilege[47]+
         "',cir_348='"+pre_cir_privilege[48]+"',cir_349='"+pre_cir_privilege[49]+"',cir_350='"+pre_cir_privilege[50]+
         "',cir_351='"+pre_cir_privilege[51]+"',cir_352='"+pre_cir_privilege[52]+"',cir_353='"+pre_cir_privilege[53]+
         "',cir_354='"+pre_cir_privilege[54]+"',cir_355='"+pre_cir_privilege[55]+"',cir_356='"+pre_cir_privilege[56]+
         "',cir_357='"+pre_cir_privilege[57]+"',cir_358='"+pre_cir_privilege[58]+"',cir_359='"+pre_cir_privilege[59]+
         "',cir_360='"+pre_cir_privilege[60]+"',cir_361='"+pre_cir_privilege[61]+"',cir_362='"+pre_cir_privilege[62]+
         "',cir_363='"+pre_cir_privilege[63]+"',cir_364='"+pre_cir_privilege[64]+"',cir_365='"+pre_cir_privilege[65]+
         "',cir_366='"+pre_cir_privilege[66]+"',cir_367='"+pre_cir_privilege[67]+"',cir_368='"+pre_cir_privilege[68]+
         "',cir_369='"+pre_cir_privilege[69]+"',cir_370='"+pre_cir_privilege[70]+"',cir_371='"+pre_cir_privilege[71]+
         "',cir_372='"+pre_cir_privilege[72]+"',cir_373='"+pre_cir_privilege[73]+"',cir_374='"+pre_cir_privilege[74]+
         "',cir_375='"+pre_cir_privilege[75]+"',cir_376='"+pre_cir_privilege[76]+"',cir_377='"+pre_cir_privilege[77]+
         "',cir_378='"+pre_cir_privilege[78]+"',cir_379='"+pre_cir_privilege[79]+"',cir_380='"+pre_cir_privilege[80]+
         "',cir_381='"+pre_cir_privilege[81]+"',cir_382='"+pre_cir_privilege[82]+"',cir_383='"+pre_cir_privilege[83]+
         "',cir_384='"+pre_cir_privilege[84]+"',cir_385='"+pre_cir_privilege[85]+"',cir_386='"+pre_cir_privilege[86]+
         "',cir_387='"+pre_cir_privilege[87]+"',cir_388='"+pre_cir_privilege[88]+
         "',cir_389='"+pre_cir_privilege[89]+"',cir_390='"+pre_cir_privilege[90]+"',cir_391='"+pre_cir_privilege[91]+
         "',cir_392='"+pre_cir_privilege[92]+"',cir_393='"+pre_cir_privilege[93]+"',cir_394='"+pre_cir_privilege[94]+
         "',cir_395='"+pre_cir_privilege[95]+"',cir_396='"+pre_cir_privilege[96]+"',cir_397='"+pre_cir_privilege[97]+
         "',cir_398='"+pre_cir_privilege[98]+"',cir_399='"+pre_cir_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";

    pre_sql5="update ser_privilege set ser_401='"+pre_ser_privilege[1]+"',ser_402='"+pre_ser_privilege[2]+
         "',ser_403='"+pre_ser_privilege[3]+"',ser_404='"+pre_ser_privilege[4]+"',ser_405='"+pre_ser_privilege[5]+
         "',ser_406='"+pre_ser_privilege[6]+"',ser_407='"+pre_ser_privilege[7]+"',ser_408='"+pre_ser_privilege[8]+
         "',ser_409='"+pre_ser_privilege[9]+"',ser_410='"+pre_ser_privilege[10]+"',ser_411='"+pre_ser_privilege[11]+
         "',ser_412='"+pre_ser_privilege[12]+"',ser_413='"+pre_ser_privilege[13]+"',ser_414='"+pre_ser_privilege[14]+
         "',ser_415='"+pre_ser_privilege[15]+"',ser_416='"+pre_ser_privilege[16]+"',ser_417='"+pre_ser_privilege[17]+
         "',ser_418='"+pre_ser_privilege[18]+"',ser_419='"+pre_ser_privilege[19]+"',ser_420='"+pre_ser_privilege[20]+
         "',ser_421='"+pre_ser_privilege[21]+"',ser_422='"+pre_ser_privilege[22]+"',ser_423='"+pre_ser_privilege[23]+
         "',ser_424='"+pre_ser_privilege[24]+"',ser_425='"+pre_ser_privilege[25]+"',ser_426='"+pre_ser_privilege[26]+
         "',ser_427='"+pre_ser_privilege[27]+"',ser_428='"+pre_ser_privilege[28]+"',ser_429='"+pre_ser_privilege[29]+
         "',ser_430='"+pre_ser_privilege[30]+"',ser_431='"+pre_ser_privilege[31]+"',ser_432='"+pre_ser_privilege[32]+
         "',ser_433='"+pre_ser_privilege[33]+"',ser_434='"+pre_ser_privilege[34]+"',ser_435='"+pre_ser_privilege[35]+
         "',ser_436='"+pre_ser_privilege[36]+"',ser_437='"+pre_ser_privilege[37]+"',ser_438='"+pre_ser_privilege[38]+
         "',ser_439='"+pre_ser_privilege[39]+"',ser_440='"+pre_ser_privilege[40]+"',ser_441='"+pre_ser_privilege[41]+
         "',ser_442='"+pre_ser_privilege[42]+"',ser_443='"+pre_ser_privilege[43]+"',ser_444='"+pre_ser_privilege[44]+
         "',ser_445='"+pre_ser_privilege[45]+"',ser_446='"+pre_ser_privilege[46]+"',ser_447='"+pre_ser_privilege[47]+
         "',ser_448='"+pre_ser_privilege[48]+"',ser_449='"+pre_ser_privilege[49]+"',ser_450='"+pre_ser_privilege[50]+
         "',ser_451='"+pre_ser_privilege[51]+"',ser_452='"+pre_ser_privilege[52]+"',ser_453='"+pre_ser_privilege[53]+
         "',ser_454='"+pre_ser_privilege[54]+"',ser_455='"+pre_ser_privilege[55]+"',ser_456='"+pre_ser_privilege[56]+
         "',ser_457='"+pre_ser_privilege[57]+"',ser_458='"+pre_ser_privilege[58]+"',ser_459='"+pre_ser_privilege[59]+
         "',ser_460='"+pre_ser_privilege[60]+"',ser_461='"+pre_ser_privilege[61]+"',ser_462='"+pre_ser_privilege[62]+
         "',ser_463='"+pre_ser_privilege[63]+"',ser_464='"+pre_ser_privilege[64]+"',ser_465='"+pre_ser_privilege[65]+
         "',ser_466='"+pre_ser_privilege[66]+"',ser_467='"+pre_ser_privilege[67]+"',ser_468='"+pre_ser_privilege[68]+
         "',ser_469='"+pre_ser_privilege[69]+"',ser_470='"+pre_ser_privilege[70]+"',ser_471='"+pre_ser_privilege[71]+
         "',ser_472='"+pre_ser_privilege[72]+"',ser_473='"+pre_ser_privilege[73]+"',ser_474='"+pre_ser_privilege[74]+
         "',ser_475='"+pre_ser_privilege[75]+"',ser_476='"+pre_ser_privilege[76]+"',ser_477='"+pre_ser_privilege[77]+
         "',ser_478='"+pre_ser_privilege[78]+"',ser_479='"+pre_ser_privilege[79]+"',ser_480='"+pre_ser_privilege[80]+
         "',ser_481='"+pre_ser_privilege[81]+"',ser_482='"+pre_ser_privilege[82]+"',ser_483='"+pre_ser_privilege[83]+
         "',ser_484='"+pre_ser_privilege[84]+"',ser_485='"+pre_ser_privilege[85]+"',ser_486='"+pre_ser_privilege[86]+
         "',ser_487='"+pre_ser_privilege[87]+"',ser_488='"+pre_ser_privilege[88]+
         "',ser_489='"+pre_ser_privilege[89]+"',ser_490='"+pre_ser_privilege[90]+"',ser_491='"+pre_ser_privilege[91]+
         "',ser_492='"+pre_ser_privilege[92]+"',ser_493='"+pre_ser_privilege[93]+"',ser_494='"+pre_ser_privilege[94]+
         "',ser_495='"+pre_ser_privilege[95]+"',ser_496='"+pre_ser_privilege[96]+"',ser_497='"+pre_ser_privilege[97]+
         "',ser_498='"+pre_ser_privilege[98]+"',ser_499='"+pre_ser_privilege[99]+"' where staff_id='"+
         staff_id+"' and library_id='"+library_id+"'";
//System.out.println(sql1);
//System.out.println(sql2);
//System.out.println(sql3);
//System.out.println(sql4);
//System.out.println(sql5);
   MyQueryResult.getMyExecuteUpdate(pre_sql1);
   MyQueryResult.getMyExecuteUpdate(pre_sql2);
   MyQueryResult.getMyExecuteUpdate(pre_sql3);
   MyQueryResult.getMyExecuteUpdate(pre_sql4);
   MyQueryResult.getMyExecuteUpdate(pre_sql5);

   }

}
