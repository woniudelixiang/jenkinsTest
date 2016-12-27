

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>textarea还剩余字数统计 - 懒人建站 http://www.51xuediannao.com/</title>
<style type="text/css">
body, a {
	font-size: 14px;
	color: #555;;
}

.wordCount {
	position: relative;
	width: 600px;
}

.wordCount textarea {
	width: 100%;
	height: 100px;
}

.wordCount .wordwrap {
	position: absolute;
	right: 6px;
	bottom: 6px;
}

.wordCount .word {
	color: red;
	padding: 0 4px;;
}
</style>
</head>
<body>
	<h1>textarea还剩余字数统计</h1>

	<p>textarea还剩余字数统计，支持复制粘贴的时候统计字数</p>

	<div class="wordCount" id="wordCount">
		<textarea placeholder="textarea还剩余字数统计"></textarea>
		<span class="wordwrap"><var class="word">200</var>/200</span>
	</div>


	<span id="clock"></span>
	<script src="http://lib.sinaapp.com/js/jquery/1.10.2/jquery-1.10.2.min.js"></script><script>
    $(function(){
        //先选出 textarea 和 统计字数 dom 节点
        var wordCount = $("#wordCount"),
                textArea = wordCount.find("textarea"),
                word = wordCount.find(".word");
        //调用
        statInputNum(textArea,word);

    });
    
    /*
     * 剩余字数统计
     * 注意 最大字数只需要在放数字的节点哪里直接写好即可 如：<var class="word">200</var>
     */
    function statInputNum(textArea,numItem) {
        var max = numItem.text(),
                curLength;
        textArea[0].setAttribute("maxlength", max);
        curLength = textArea.val().length;
        numItem.text(max - curLength);
        textArea.on('input propertychange', function () {
            var _value = $(this).val().replace(/\n/gi,"");
            numItem.text(max - _value.length);
        });
    }
</script>
	
</body>
</html>
