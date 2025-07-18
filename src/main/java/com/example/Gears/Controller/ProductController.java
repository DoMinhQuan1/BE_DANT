package com.example.Gears.Controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.Chromaticity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Gears.Common.CommonUtils;
import com.example.Gears.Entity.Product;
import com.example.Gears.Model.Response;
import com.example.Gears.Request.ProductRequest;
import com.example.Gears.Service.ProductService;
import com.example.Gears.Service.PriceHistoryService;
import com.example.Gears.ServiceImpl.ProductServiceImpl;
import com.example.Gears.Entity.PriceHistory;


@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
    @Autowired
    private PriceHistoryService priceHistoryService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Product>> getProduct(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<Response<Product>>(new Response<Product>(productService.findById(id)), HttpStatus.OK);
	}

	@GetMapping(value = "/export")
	public ResponseEntity<InputStreamResource> exportProduct() {
		ByteArrayInputStream in;
		try {
			ProductRequest ProductRequest = new ProductRequest();
			in = productService.exportExcelProduct(ProductRequest);
			HttpHeaders headers = new HttpHeaders();
			String date = CommonUtils.StringFormatDate(new Date(), "dd/MM/yyyy");
			headers.add("Content-Disposition", "attachment; filename=BaoCao" + date + ".xlsx");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/statistic-brand")
	public ResponseEntity<Response<List<Long>>> getSatisticBrand() {
		List<Long> statics = productService.getSatisticBrand();
		
		return new ResponseEntity<Response<List<Long>>>(new Response<List<Long>>(statics), HttpStatus.OK);
	}

	@GetMapping(value = "/statistic-category")
	public ResponseEntity<Response<List<Long>>> getSatisticCategory() {
		List<Long> statics = productService.getSatisticCategory();
		return new ResponseEntity<Response<List<Long>>>(new Response<List<Long>>(statics), HttpStatus.OK);
	}

	@GetMapping(value = "")
	public ResponseEntity<Response<Product>> getProducts(ProductRequest productRequest) {
		int page = productRequest.getPageIndex() - 1;
		int size = productRequest.getPageSize();
		Sort sortable = null;
		if (productRequest.getSortField() != null && !productRequest.getSortField().equalsIgnoreCase("null")) {
			if (productRequest.getSortOrder().equals("ascend")) {
				sortable = Sort.by(productRequest.getSortField()).ascending();
			}
			if (productRequest.getSortOrder().equals("descend")) {
				sortable = Sort.by(productRequest.getSortField()).descending();
			}
		} else {
			sortable = Sort.by("productID").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);
		Page<Product> pageBrandPage = productService.findProduct(productRequest, pageable);
		List<Product> lists = pageBrandPage.toList();
		Long count = (long) pageBrandPage.getTotalElements();
		return new ResponseEntity<Response<Product>>(new Response<Product>(count, lists), HttpStatus.OK);
	}
	@GetMapping(value = "/search")
	public ResponseEntity<List<Product>> getProductSeach(ProductRequest productRequest) {
		int page = productRequest.getPageIndex() - 1;
		int size = productRequest.getPageSize();
		Sort sortable = null;
		if (productRequest.getSortField() != null && !productRequest.getSortField().equalsIgnoreCase("null")) {
			if (productRequest.getSortOrder().equals("ascend")) {
				sortable = Sort.by(productRequest.getSortField()).ascending();
			}
			if (productRequest.getSortOrder().equals("descend")) {
				sortable = Sort.by(productRequest.getSortField()).descending();
			}
		} else {
			sortable = Sort.by("productID").descending();
		}
		Pageable pageable = PageRequest.of(page, size, sortable);
		Page<Product> pageBrandPage = productService.findProduct(productRequest, pageable);
		List<Product> lists = pageBrandPage.toList();
		return new ResponseEntity<List<Product>>(lists, HttpStatus.OK);
	}
	
	@PostMapping(value = "/price-history")
	public ResponseEntity<String> savePriceHistory(@RequestBody PriceHistory priceHistory) {
	    priceHistoryService.savePriceHistory(priceHistory);
	    return ResponseEntity.ok("Price history saved successfully");
	}
	
	// Lấy lịch sử giá
	@GetMapping(value = "/{id}/price-history")
	public ResponseEntity<List<PriceHistory>> getPriceHistory(@PathVariable Long id) {
	    List<PriceHistory> history = priceHistoryService.getPriceHistory(id);
	    return ResponseEntity.ok(history);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Response<Product>> saveProduct(@RequestBody Product product) {
		System.out.println("Received product: " + product);
		if (product != null) {
			try {
				Product savedProduct = productService.saveProduct(product);
				return new ResponseEntity<Response<Product>>(new Response<Product>(savedProduct), HttpStatus.OK);
			} catch (Exception e) {
				System.err.println("Error saving product: " + e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<Response<Product>>(new Response<Product>("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Response<Product>>(new Response<Product>("loi", "10001"), HttpStatus.OK);
	}
	
	@GetMapping(value = "/test")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Product API is working!");
	}
	
	// update image
		@RequestMapping(path = "/upload_image/{id}", method = RequestMethod.POST, consumes = { "multipart/form-data" })
		public ResponseEntity<Response<String>> fileUpload(@PathVariable(name = "id") Long id,
				@RequestParam("file") MultipartFile multipartFile) {
			String rootFileUpload = CommonUtils.ROOT_IMAGES_BACKEND;
			String rootFileUpload1 = CommonUtils.ROOT_IMAGES_FRONTEND;
//			String rootFileUpload = "/home/app/bidv_run";
			Product product = productService.findById(id);

			if (product != null) {
				String originalFilename = multipartFile.getOriginalFilename();
				product.setProductImage("images/" + originalFilename);
				File file = new File(rootFileUpload + originalFilename);
				if (file.getParentFile().exists() == false) {
					file.getParentFile().mkdirs();
				}
				File file1 = new File(rootFileUpload1 + originalFilename);
				if (file1.getParentFile().exists() == false) {
					file1.getParentFile().mkdirs();
				}
				try {
					try (InputStream is = multipartFile.getInputStream()) {
						try (OutputStream os = new FileOutputStream(file)) {
							byte[] b = new byte[10240];
							int size = 0;
							while ((size = is.read(b)) != -1) {
								os.write(b, 0, size);
							}
						}

						is.close();
					}
					try (InputStream is1 = multipartFile.getInputStream()) {
						try (OutputStream os = new FileOutputStream(file1)) {
							byte[] b = new byte[10240];
							int size = 0;
							while ((size = is1.read(b)) != -1) {
								os.write(b, 0, size);
							}
						}
						is1.close();
					}
					// save product
					productService.saveProduct(product);
					return new ResponseEntity<Response<String>>(new Response<String>("Update thành công"), HttpStatus.OK);
				} catch (IOException e) {
					e.printStackTrace();
					return new ResponseEntity<Response<String>>(new Response<String>("Có lỗi"), HttpStatus.BAD_REQUEST);
				}
			}
			return ResponseEntity.notFound().build();
		}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Response<Product>> deleteProduct(@PathVariable(name = "id") Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<Response<Product>>(new Response<Product>("xoa thanh cong", "200"), HttpStatus.OK);
	}

}
