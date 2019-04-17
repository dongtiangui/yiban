/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	config.enterMode = CKEDITOR.ENTER_BR;
	config.shiftEnterMode = CKEDITOR.ENTER_BR;
	config.removePlugins = 'elementspath';

	config.extraPlugins = 'html5video';

	config.smiley_path = 'plugins/smiley/images/';

	config.smiley_images = [
		'regular_smile.gif','sad_smile.gif','wink_smile.gif','teeth_smile.gif','confused_smile.gif','tounge_smile.gif',
		'embaressed_smile.gif','omg_smile.gif','whatchutalkingabout_smile.gif','angry_smile.gif','angel_smile.gif','shades_smile.gif',
		'devil_smile.gif','cry_smile.gif','lightbulb.gif','thumbs_down.gif','thumbs_up.gif','heart.gif',
		'broken_heart.gif','kiss.gif','envelope.gif'];

	config.colorButton_backStyle = {
        element : 'span',
        styles : { 'background-color' : '#(color)' }
    }
    config.colorButton_colors = '000,800000,8B4513,2F4F4F,008080,000080,4B0082,696969,B22222,A52A2A,DAA520,006400,40E0D0,0000CD,800080,808080,F00,FF8C00,FFD700,008000,0FF,00F,EE82EE,A9A9A9,FFA07A,FFA500,FFFF00,00FF00,AFEEEE,ADD8E6,DDA0DD,D3D3D3,FFF0F5,FAEBD7,FFFFE0,F0FFF0,F0FFFF,F0F8FF,E6E6FA,FFF'

    config.colorButton_enableMore = false;
    config.colorButton_foreStyle = {
        element : 'span',
        styles : { 'color' : '#(color)' }
    };
	config.autoUpdateElement = true;
	config.format_tags = 'p;h1;h2;h3;pre';

	config.image_previewText = ' ';
	
	config.language = 'zh-cn';
	
	config.uiColor = '#FF4040';
	
};
