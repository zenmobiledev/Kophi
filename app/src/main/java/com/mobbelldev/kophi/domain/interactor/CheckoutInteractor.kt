package com.mobbelldev.kophi.domain.interactor

import com.mobbelldev.kophi.domain.usecase.CheckoutUseCase
import com.mobbelldev.kophi.domain.usecase.DecrementQuantityUseCase
import com.mobbelldev.kophi.domain.usecase.DeleteAllOrdersUseCase
import com.mobbelldev.kophi.domain.usecase.DeleteCoffeeCartUseCase
import com.mobbelldev.kophi.domain.usecase.GetAllCartCoffeesUseCase
import com.mobbelldev.kophi.domain.usecase.GetEmailUseCase
import com.mobbelldev.kophi.domain.usecase.GetTokenUseCase
import com.mobbelldev.kophi.domain.usecase.GetUserIdUseCase
import com.mobbelldev.kophi.domain.usecase.IncrementQuantityUseCase
import com.mobbelldev.kophi.domain.usecase.UpdateQuantityAndSubtotalUseCase
import javax.inject.Inject

class CheckoutInteractor @Inject constructor(
    val checkoutUseCase: CheckoutUseCase,
    val getUserIdUseCase: GetUserIdUseCase,
    val getTokenUseCase: GetTokenUseCase,
    val getEmailUseCase: GetEmailUseCase,
    val incrementQuantityUseCase: IncrementQuantityUseCase,
    val decrementQuantityUseCase: DecrementQuantityUseCase,
    val updateQuantityAndSubtotalUseCase: UpdateQuantityAndSubtotalUseCase,
    val getAllCartCoffeesUseCase: GetAllCartCoffeesUseCase,
    val deleteCoffeeCartUseCase: DeleteCoffeeCartUseCase,
    val deleteAllOrdersUseCase: DeleteAllOrdersUseCase,
)