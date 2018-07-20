package carriage.com.carriagerestaurantapp.dagger

import dagger.Module
import android.arch.lifecycle.ViewModel
import carriage.com.carriagerestaurantapp.completedsentaion.views.completedorders.CompletedOrdersViewModel
import carriage.com.carriagerestaurantapp.presentaion.views.home.HomeViewModel
import carriage.com.carriagerestaurantapp.presentaion.views.login.LoginViewModel
import carriage.com.carriagerestaurantapp.presentaion.views.neworders.NewOrdersViewModel
import carriage.com.carriagerestaurantapp.presentaion.views.receivedorders.ReceivedOrdersViewModel
import dagger.multibindings.IntoMap
import dagger.Binds

@Module
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel::class)
//    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel


}