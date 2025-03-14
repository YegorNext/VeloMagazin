package com.velomagaz.data_integration;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.springframework.stereotype.Service;

import com.velomagaz.data_integration.strategies.SportSystemParserStrategy;

@Service
public class ImportManager<T> {
	
	private final IDataBuilder<T> dataBuilder;
	private final ILinkParser linkParser;
	private final IImageDownloader imageDownloader;
	private final IBrandImporter brandImporter;
	private final IProductComponentImporter productComponentImporter;
	private final IProductImporter productImporter;
	private final IBrandFactory brandFactory;
	private final IImagePathParser imageParser;
	
	public ImportManager(IImageDownloader imageDownloader,IBrandImporter brandImporter,
						 IProductComponentImporter productComponentImporter, IProductImporter productImporter,
						 IBrandFactory brandFactory, IDataBuilder<T> dataBuilder) {
		this.dataBuilder = dataBuilder;
		this.imageDownloader = imageDownloader;
		this.brandImporter = brandImporter;
		this.productComponentImporter = productComponentImporter;
		this.productImporter = productImporter;
		this.brandFactory = brandFactory;
		
		this.linkParser = new LinkParserVeloTrade();
		this.imageParser = new ImagePathParser();
	}
	
	public void importData(T data) throws EncryptedDocumentException, IOException {		
		dataBuilder.build(data);
		
		
		brandImporter.importBrandList(brandFactory.build(dataBuilder.getDataSet()));
		System.out.println("INFO: Brands was imported");
		
		productImporter.importData(dataBuilder.getDataSet(), imageParser.parseAll("images"));
		System.out.println("INFO: Products was imported");
		

		//productComponentImporter.importData(dataBuilder.getDataSet());
		//System.out.println("INFO: Product Components was imported");
		
	}

}
