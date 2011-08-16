//<script type="text/javascript">
        var editor = null;
        function initEditor() {
                editor = new HTMLArea("ta");
                editor.generate();
                return false;
                var cfg = editor.config;
                cfg.registerButton({
                        id        : "my-hilite",
                        tooltip   : "Highlight text",
                        image     : "ed_custom.gif",
                        textMode  : false,
                        action    : function(editor) {
                                editor.surroundHTML("<span class=\"hilite\">", "</span>");
                        },
                        context   : 'table'
                });
                cfg.toolbar.push(["linebreak", "my-hilite"]); var cfg = editor.config;
                function clickHandler(editor, buttonId) {
                        switch (buttonId) {
                                case "my-toc":
                                        editor.insertHTML("<h1>Table Of Contents</h1>");
                                break;
                                case "my-date":
                                        editor.insertHTML((new Date()).toString());
                                break;
                                case "my-bold":
                                        editor.execCommand("bold");
                                        editor.execCommand("italic");
                                break;
                                case "my-hilite":
                                        editor.surroundHTML("<span class=\"hilite\">", "</span>");
                                break;
                        }
                };
                Cfg.registerButton("my-toc",  "Insert TOC", "ed_custom.gif", false, clickHandler);
                cfg.registerButton("my-date", "Insert date/time", "ed_custom.gif", false, clickHandler);
                cfg.registerButton("my-bold", "Toggle bold/italic", "ed_custom.gif", false, clickHandler);
                cfg.registerButton("my-hilite", "Hilite selection", "ed_custom.gif", false, clickHandler);
                cfg.registerButton("my-sample", "Class: sample", "ed_custom.gif", false,
                        function(editor) {
                                if (HTMLArea.is_ie) {
                                        editor.insertHTML("<span class=\"sample\">&nbsp;&nbsp;</span>");
                                        var r = editor._doc.selection.createRange();
                                        r.move("character", -2); r.moveEnd("character", 2); r.select();
                                } else {
                                        var n = editor._doc.createElement("span"); n.className = "sample"; editor.insertNodeAtSelection(n);
                                        var sel = editor._iframe.contentWindow.getSelection();  sel.removeAllRanges(); var r = editor._doc.createRange();
                                        r.setStart(n, 0); r.setEnd(n, 0); sel.addRange(r);
                                }
                        }
                );
                cfg.pageStyle = "body { background-color:  } .hilite { background-color: yellow; } "+ ".sample { color: green; font-family: moospace; }";
                cfg.toolbar.push(["linebreak", "my-toc", "my-date", "my-bold", "my-hilite", "my-sample"]);
               editor.generate();
        }
        function insertHTML() { var html = prompt("Enter some HTML code here"); if (html) { editor.insertHTML(html); } }
        function highlight() { editor.surroundHTML('<span style="background-color: yellow">', '</span>'); }
//</script>
