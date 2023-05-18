package com.example.banks_sprin_kotlin.controller

import com.example.banks_sprin_kotlin.jwt.JwtTokenUtil
import com.example.banks_sprin_kotlin.model.JwtRequest
import com.example.banks_sprin_kotlin.model.JwtResponse
import com.example.banks_sprin_kotlin.service.JwtUserDetailsService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
class JwtAuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: JwtUserDetailsService
) {

    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
        authenticate(authenticationRequest.username, authenticationRequest.password)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)

        val token: String = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok(JwtResponse(token))

    }

    @Throws(Exception::class)
    private fun authenticate(username: String?, password: String?) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}