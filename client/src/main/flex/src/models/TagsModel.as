package models
{
	import flash.display.Bitmap;
	
	import models.supportClasses.CocktailTypes;
	import models.supportClasses.OptionParameters;
	
	import spark.components.Image;

	public class TagsModel
	{
		private static var _instance:TagsModel;
		
		public static function get instance():TagsModel
		{
			if (!_instance)
				_instance = new TagsModel;
			
			return _instance;
		}
		
		public static const COCKTAIL_TYPE_TAG:String = "cocktailTypeTag";
		public static const COCKTAIL_OPTION_TAG:String = "cocktailOptionTag";
		
		[Embed(source="/../assets/tag_example.png")]
		private var tagExampleBitmap:Class;
		
		public function TagsModel()
		{
			tagCache = {};
			//
			addTagToCache(new tagExampleBitmap(), "Long drink", CocktailTypes.LONG_DRINK, COCKTAIL_TYPE_TAG);
			addTagToCache(new tagExampleBitmap(), "Short drink", CocktailTypes.SHORT_DRINK, COCKTAIL_TYPE_TAG);
			addTagToCache(new tagExampleBitmap(), "Shooter", CocktailTypes.SHOOTER, COCKTAIL_TYPE_TAG);
			addTagToCache(new tagExampleBitmap(), "Горящий", OptionParameters.BURNING, COCKTAIL_OPTION_TAG);
			addTagToCache(new tagExampleBitmap(), "Со льдом", OptionParameters.WITH_ICE, COCKTAIL_OPTION_TAG);
			addTagToCache(new tagExampleBitmap(), "Проверен администрацией", OptionParameters.CHECKED, COCKTAIL_OPTION_TAG);
			addTagToCache(new tagExampleBitmap(), "Утвержден IBA", OptionParameters.IBA, COCKTAIL_OPTION_TAG);
			addTagToCache(new tagExampleBitmap(), "Слоеный", OptionParameters.FLACKY, COCKTAIL_OPTION_TAG);
		}
		
		private var tagCache:Object;
		
		private function addTagToCache(image:Bitmap, title:String, id:Number, type:String):Image
		{
			var newTag:Image = new Image();
			newTag.smooth = true;
			newTag.cacheAsBitmap = true;
			newTag.source = image;
			newTag.toolTip = title;
			tagCache[type + id] = newTag;
			return newTag;
		}
		
		public function getTagByIdAndType(id:Number, type:String, size:Number):Image
		{
			if (size < 1)
				return null;
			
			var tag:Image = tagCache[type + id];
			
			if (tag)
			{
				tag.width = size;
				tag.height = size;
			}
			
			return tag;
		}
	}
}