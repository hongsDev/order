package com.hongsdev.order.controller;

import com.hongsdev.order.controller.dto.OrderResponseDto;
import com.hongsdev.order.domain.dto.OrderInDto;
import com.hongsdev.order.domain.dto.OrderOutDto;
import com.hongsdev.order.servie.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private static final String FILE_UPLOAD = "/tmp/desktop/";
    private final OrderService orderService;


    @GetMapping("/all")
    public ResponseEntity getAll() {
        List<OrderOutDto> all = orderService.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping("/")
    public ResponseEntity<OrderOutDto> create() {
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderOutDto());
    }

    @PostMapping("/upload")
    public ResponseEntity fileUpload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    @PutMapping("/update/{orderId}")
    public ResponseEntity update() {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity delete(@PathVariable Long orderId) {
        //orderService.orderCancel();


        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity cancel(@PathVariable Long orderId) {
        OrderInDto inDto = new OrderInDto();
        inDto.setOrderId(orderId);

        orderService.cancel(inDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);

    }
}
