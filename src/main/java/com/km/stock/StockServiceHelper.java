package com.km.stock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StockServiceHelper {
    public static void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }

    public static void removeStock(String symbol) {
        stocks.remove(symbol);
    }

    public static Stock getStock(String symbol) {
        return stocks.get(symbol);
    }

    public static Stock[] getAllStocks(){
    	Iterator<Map.Entry<String, Stock>> itr = stocks.entrySet().iterator();
    	Stock s[] = new Stock[stocks.size()];
    	int index=0;
        while(itr.hasNext()){
          s[index] = itr.next().getValue();
          index++;
        }
        
    	return s;
    }
    private static Map<String, Stock> stocks = new HashMap<String, Stock>();

    static {
        generateStocks();
    }

    private static void generateStocks() {
        addStock(new Stock("IBM", 43.12, "USD", "USA"));
        addStock(new Stock("AAPL", 120.0, "USD", "USA"));
        addStock(new Stock("GOOG", 200.0, "USD", "USA"));
     }
}