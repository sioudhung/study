import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//建立索引
        String text1 = "IK Analyzer是一个结合词典分词和文法分词的中文分词开源工具包。它使用了全新的正向迭代最细粒度切" +
        		"分算法。";
        String text2 = "中文分词工具包可以和lucene是一起使用的";
        String text3 = "中文分词,你妹";
        String fieldName = "contents";
        Analyzer analyzer = new IKAnalyzer();
        RAMDirectory directory = new RAMDirectory();
        IndexWriterConfig writerConfig = new IndexWriterConfig(Version.LATEST, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, writerConfig);
        Document document1 = new Document();
        document1.add(new Field("ID", "1", Field.Store.YES, Field.Index.NOT_ANALYZED));
        document1.add(new Field(fieldName, text1, Field.Store.YES, Field.Index.ANALYZED));
        indexWriter.addDocument(document1);
        
        Document document2 = new Document();
        document2.add(new Field("ID", "2", Field.Store.YES, Field.Store.NO));
        document2.add(new Field(fieldName, text2, Field.Store.YES, Field.Index.ANALYZED));
        indexWriter.addDocument(document2);
        
        Document document3 = new Document();
        document3.add(new Field("ID", "2", Field.Store.YES, Field.Index.NOT_ANALYZED));
        document3.add(new Field(fieldName, text3, Field.Store.YES, Field.Index.ANALYZED));
        indexWriter.addDocument(document3);
        indexWriter.close();
        
       
        //搜索
        IndexReader indexReader = IndexReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(indexReader);
        String request = "中文分词工具包";
        QueryParser parser = new QueryParser(Version.LUCENE_40, fieldName, analyzer);
        parser.setDefaultOperator(QueryParser.AND_OPERATOR);
        try {
			Query query = parser.parse(request);
			TopDocs topDocs = searcher.search(query, 5);
			System.out.println("命中数:"+topDocs.totalHits);
			ScoreDoc[] docs = topDocs.scoreDocs;
			for(ScoreDoc doc : docs){
				Document d = searcher.doc(doc.doc);
				System.out.println("内容:"+d.get(fieldName));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(indexReader != null){
				try{
					indexReader.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(directory != null){
				try{
					directory.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
        
	}

}