class Example {
   static void main(String[] args) {
       def proc = "git diff HEAD HEAD^ go.sum".execute()
       def b = new StringBuffer()
       proc.consumeProcessErrorStream(b)

       println proc.text
       println b.toString()
   }
}