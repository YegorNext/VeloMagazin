package com.velomagaz.data_integration;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.springframework.stereotype.Service;

@Service
public class ImportManager<T> {
	
	private final IDataBuilder<T> dataBuilder;
	private final ILinkParser linkParser;
	private final IImageDownloader imageDownloader;
	private final IBrandImporter brandImporter;
	private final IProductComponentImporter productComponentImporter;
	private final IProductImporter productImporter;
	private final IBrandFactory brandFactory;
	
	public ImportManager(IImageDownloader imageDownloader,IBrandImporter brandImporter,
						 IProductComponentImporter productComponentImporter, IProductImporter productImporter,
						 IBrandFactory brandFactory, IDataBuilder<T> dataBuilder, ILinkParser linkParser) {
		this.dataBuilder = dataBuilder;
		this.linkParser = linkParser;
		this.imageDownloader = imageDownloader;
		this.brandImporter = brandImporter;
		this.productComponentImporter = productComponentImporter;
		this.productImporter = productImporter;
		this.brandFactory = brandFactory;
	}
	
	public void importData(T data) throws EncryptedDocumentException, IOException {		
		dataBuilder.build(data);
		
		brandImporter.importBrandList(brandFactory.build(dataBuilder.getDataSet()));
		productImporter.importData(dataBuilder.getDataSet(), imageDownloader.download(linkParser.parseData(dataBuilder.getDataSet())));
		
		
		productComponentImporter.importData(dataBuilder.getDataSet());
		
	}

}
