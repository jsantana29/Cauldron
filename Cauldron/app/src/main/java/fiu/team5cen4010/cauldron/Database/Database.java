package fiu.team5cen4010.cauldron.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import fiu.team5cen4010.cauldron.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "cauldron.db";
    private static final int DB_VER = 4;

    public Database(Context context) {
        super(context, DB_NAME, null,DB_VER);
    }

    //Function to display all recipe information(whole table)
    public List<Recipe> getRecipe()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Attributes as they appear in the database
        String[] sqlSelect = {"recipeid","name","type","description","ingredients","instruction","imageid","nutritionimage","cookingtime","totalcost"};
        String RECIPE_TABLE = "Recipe"; //Table name as it is in database

        qb.setTables(RECIPE_TABLE);
        Cursor cursor = qb.query(db,sqlSelect, null,null, null, null, null);
        List<Recipe> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Recipe recipe = new Recipe();
                recipe.setRecipeid(cursor.getInt(cursor.getColumnIndex("recipeid")));
                recipe.setName(cursor.getString(cursor.getColumnIndex("name")));
                recipe.setType(cursor.getString(cursor.getColumnIndex("type")));
                recipe.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex("ingredients")));
                recipe.setInstruction(cursor.getString(cursor.getColumnIndex("instruction")));
                recipe.setImageid(cursor.getString(cursor.getColumnIndex("imageid")));
                recipe.setNutritionimage(cursor.getString(cursor.getColumnIndex("nutritionimage")));
                recipe.setCookingtime(cursor.getInt(cursor.getColumnIndex("cookingtime")));
                recipe.setTotalcost(cursor.getFloat(cursor.getColumnIndex("totalcost")));

                result.add(recipe);
            }while(cursor.moveToNext());
        }
        return result;
    }

    //Function to display all recipe names
    public List<String> getRecipeName()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Attributes as they appear in the database
        String[] sqlSelect = {"name"};
        String RECIPE_TABLE = "Recipe"; //Table name as it is in database

        qb.setTables(RECIPE_TABLE);
        Cursor cursor = qb.query(db,sqlSelect, null,null, null, null, null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                result.add(cursor.getString(cursor.getColumnIndex("name")));
            }while(cursor.moveToNext());
        }
        return result;
    }

    //Function to query recipes by names
    public List<Recipe> getRecipeByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Attributes as they appear in the database
        String[] sqlSelect = {"recipeid","name","type","description","ingredients","instruction","imageid","nutritionimage","cookingtime","totalcost"};
        String RECIPE_TABLE = "Recipe"; //Table name as it is in database

        qb.setTables(RECIPE_TABLE);
        //For exact name query do: Cursor cursor = qb.query(db,sqlSelect, "name = ?",new String[]{name}, null, null, null);
        //LIKE implementation is done below
        Cursor cursor = qb.query(db,sqlSelect, "name LIKE ?",new String[]{"%"+name+"%"}, null, null, null);
        List<Recipe> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Recipe recipe = new Recipe();
                recipe.setRecipeid(cursor.getInt(cursor.getColumnIndex("recipeid")));
                recipe.setName(cursor.getString(cursor.getColumnIndex("name")));
                recipe.setType(cursor.getString(cursor.getColumnIndex("type")));
                recipe.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                recipe.setIngredients(cursor.getString(cursor.getColumnIndex("ingredients")));
                recipe.setInstruction(cursor.getString(cursor.getColumnIndex("instruction")));
                recipe.setImageid(cursor.getString(cursor.getColumnIndex("imageid")));
                recipe.setNutritionimage(cursor.getString(cursor.getColumnIndex("nutritionimage")));
                recipe.setCookingtime(cursor.getInt(cursor.getColumnIndex("cookingtime")));
                recipe.setTotalcost(cursor.getFloat(cursor.getColumnIndex("totalcost")));

                result.add(recipe);
            }while(cursor.moveToNext());
        }
        return result;
    }

}