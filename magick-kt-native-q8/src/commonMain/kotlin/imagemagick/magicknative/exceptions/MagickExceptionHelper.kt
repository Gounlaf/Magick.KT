@file:Suppress("KDocMissingDocumentation")

package imagemagick.magicknative.exceptions

import imagemagick.core.exceptions.MagickException
import imagemagick.core.exceptions.error.MagickBlobErrorException
import imagemagick.core.exceptions.error.MagickCacheErrorException
import imagemagick.core.exceptions.error.MagickCoderErrorException
import imagemagick.core.exceptions.error.MagickConfigureErrorException
import imagemagick.core.exceptions.error.MagickCorruptImageErrorException
import imagemagick.core.exceptions.error.MagickDelegateErrorException
import imagemagick.core.exceptions.error.MagickDrawErrorException
import imagemagick.core.exceptions.error.MagickErrorException
import imagemagick.core.exceptions.error.MagickFileOpenErrorException
import imagemagick.core.exceptions.error.MagickImageErrorException
import imagemagick.core.exceptions.error.MagickMissingDelegateErrorException
import imagemagick.core.exceptions.error.MagickModuleErrorException
import imagemagick.core.exceptions.error.MagickOptionErrorException
import imagemagick.core.exceptions.error.MagickPolicyErrorException
import imagemagick.core.exceptions.error.MagickRegistryErrorException
import imagemagick.core.exceptions.error.MagickResourceLimitErrorException
import imagemagick.core.exceptions.error.MagickStreamErrorException
import imagemagick.core.exceptions.error.MagickTypeErrorException
import imagemagick.core.exceptions.warning.MagickBlobWarningException
import imagemagick.core.exceptions.warning.MagickCacheWarningException
import imagemagick.core.exceptions.warning.MagickCoderWarningException
import imagemagick.core.exceptions.warning.MagickConfigureWarningException
import imagemagick.core.exceptions.warning.MagickCorruptImageWarningException
import imagemagick.core.exceptions.warning.MagickDelegateWarningException
import imagemagick.core.exceptions.warning.MagickDrawWarningException
import imagemagick.core.exceptions.warning.MagickFileOpenWarningException
import imagemagick.core.exceptions.warning.MagickImageWarningException
import imagemagick.core.exceptions.warning.MagickMissingDelegateWarningException
import imagemagick.core.exceptions.warning.MagickModuleWarningException
import imagemagick.core.exceptions.warning.MagickOptionWarningException
import imagemagick.core.exceptions.warning.MagickPolicyWarningException
import imagemagick.core.exceptions.warning.MagickRegistryWarningException
import imagemagick.core.exceptions.warning.MagickResourceLimitWarningException
import imagemagick.core.exceptions.warning.MagickStreamWarningException
import imagemagick.core.exceptions.warning.MagickTypeWarningException
import imagemagick.core.exceptions.warning.MagickWarningException
import imagemagick.helpers.isNotNullOrEmpty
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.NativePlacement
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.toKString
import kotlinx.cinterop.value
import libMagickNative.ExceptionInfo
import libMagickNative.MagickExceptionHelper_Description
import libMagickNative.MagickExceptionHelper_Dispose
import libMagickNative.MagickExceptionHelper_Message
import libMagickNative.MagickExceptionHelper_Related
import libMagickNative.MagickExceptionHelper_RelatedCount
import libMagickNative.MagickExceptionHelper_Severity
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalForeignApi
public typealias ExceptionInfoPtrVar = CPointerVar<ExceptionInfo>

// @ExperimentalForeignApi
// @ExperimentalContracts
// @Throws(MagickException::class)
// public inline fun <T : Any, O : T?> withException(body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> O): T {
//    contract {
//        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
//    }
//
//    return memScoped {
//        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()
//
//        val result = body(this, exceptionInfo.ptr)
//
//        MagickExceptionHelper.check(exceptionInfo.value)
//
//        result!!
//    }
// }

@ExperimentalForeignApi
@ExperimentalContracts
@Throws(MagickException::class)
public inline fun <T : Any, O : T?> withExceptionOnly(
    body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> O,
): Pair<O, MagickException?> {
    contract {
        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
    }

    return memScoped {
        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()

        val result = body(this, exceptionInfo.ptr)

        val exception = MagickExceptionHelper.check(exceptionInfo.value)

        Pair(result, exception)
    }
}

@ExperimentalForeignApi
@ExperimentalContracts
@Throws(MagickException::class)
public inline fun <T : Any, O : T?> withException(
    body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> O,
): Pair<T, MagickException?> {
    contract {
        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
    }

    return memScoped {
        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()

        val r = body(this, exceptionInfo.ptr)

        val (result, exception) =
            MagickExceptionHelper.checkException(exceptionInfo.value, r) {
                // NO-OP
            }

        Pair(result, exception)
    }
}

@ExperimentalForeignApi
@ExperimentalContracts
@Throws(MagickException::class)
public inline fun <T : Any, O : T?> withException(
    noinline onError: ((T) -> Unit),
    body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> O,
): Pair<T, MagickException?> {
    contract {
        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
    }

    return memScoped {
        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()

        val r = body(this, exceptionInfo.ptr)

        val (result, exception) =
            MagickExceptionHelper.checkException(exceptionInfo.value, r) {
                onError(it)
            }

        Pair(result, exception)
    }
}

// @ExperimentalForeignApi
// @ExperimentalContracts
// @Throws(MagickException::class)
// public inline fun <T : Any, O : T?> withException(
// //    noinline onError: ((T) -> Unit)? = null,
//    body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> O,
// ): Pair<T, MagickException?> {
//    contract {
//        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
//    }
//
//    return memScoped {
//        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()
//
//        val r = body(this, exceptionInfo.ptr)
//
//        val (result, exception) = MagickExceptionHelper.checkException(exceptionInfo.value, r) {
// //            onError?.invoke(it)
//        }
//
//        Pair(result, exception)
//    }
// }

// @ExperimentalForeignApi
// @ExperimentalContracts
// @Throws(MagickException::class)
// public inline fun withException(
//    body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> CPointer<Image>?,
// ): Pair<CPointer<Image>, MagickException?> {
//    contract {
//        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
//    }
//
//    return memScoped {
//        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()
//
//        val r = body(this, exceptionInfo.ptr)
//
//        val (result, exception) = MagickExceptionHelper.checkException(exceptionInfo.value, r) {
//            it.dispose()
//        }
//
//        Pair(result, exception)
//    }
// }

// @ExperimentalForeignApi
// @ExperimentalContracts
// @Throws(MagickException::class)
// public inline fun <T : Any, O : T?> withException(crossinline onError: (T) -> Unit, body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> O): Pair<O, MagickException?> {
//    contract {
//        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
//    }
//
//    return memScoped {
//        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()
//
//        val result = body(this, exceptionInfo.ptr)
//
//        val exception = MagickExceptionHelper.checkException(exceptionInfo.value, result) {
//            onError(it)
//        }
//
//        Pair(result, exception)
//    }
// }

// @ExperimentalForeignApi
// @ExperimentalContracts
// @Throws(MagickException::class)
// public inline fun <U : CVariable, O : CPointer<U>?> withException(nativeInstance: NativeInstance<U>, body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> O) {
//    contract {
//        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
//    }
//
//    return memScoped {
//        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()
//
//        val result = body(this, exceptionInfo.ptr)
//
//        nativeInstance.ptr = nativeInstance.checkException(exceptionInfo.value, result)
//    }
// }

// @ExperimentalForeignApi
// @ExperimentalContracts
// @Throws(MagickException::class)
// public inline fun <T> withException(
//    onError: (T) -> Unit,
//    body: (NativePlacement, CPointer<CPointerVar<ExceptionInfo>>) -> T,
// ): T {
//    contract {
//        callsInPlace(body, InvocationKind.EXACTLY_ONCE)
//    }
//
//    return memScoped {
//        val exceptionInfo = alloc<CPointerVar<ExceptionInfo>>()
//
//        val result = body(this, exceptionInfo.ptr)
//
//        try {
//            MagickExceptionHelper.check(exceptionInfo.value)
//        } catch (e: MagickErrorException) {
//            onError(result)
//        }
//
//        result
//    }
// }

@ExperimentalForeignApi
public object MagickExceptionHelper {
    public fun <T> checkException(
        exception: CPointer<ExceptionInfo>?,
        result: T?,
        onError: (T) -> Unit,
    ): Pair<T, MagickException?> {
        val magickException = create(exception)
//        if (magickException == null) {
//            if (result == null) {
//                throw MagickErrorException("The operation returned null but did not raise an exception.")
//            }
//
//            return Pair(result, magickException)
//        }

        if (magickException is MagickErrorException) {
            result?.let { onError(it) }

            throw magickException
        }

        if (result == null) {
            throw MagickErrorException("The operation returned null but did not raise an exception.")
        }

        return Pair(result, magickException)
    }

    @Throws(MagickException::class)
    public fun check(ptr: CPointerVar<ExceptionInfo>): MagickException? = check(ptr.value)

    @Throws(MagickException::class)
    public fun check(ptr: CPointer<ExceptionInfo>?): MagickException? {
        val magickException = create(ptr) ?: return null

        if (magickException is MagickErrorException) {
            throw magickException
        }

        return magickException
    }

    public fun create(ptr: CPointer<ExceptionInfo>?): MagickException? =
        ptr?.let {
            val magickException = createException(it)

            MagickExceptionHelper_Dispose(it)

            magickException
        }

    public fun createException(ptr: CPointer<ExceptionInfo>): MagickException {
        val severity: ExceptionSeverity =
            MagickExceptionHelper_Severity(ptr).let { severityValue ->
                enumValues<ExceptionSeverity>().firstOrNull { it.severity == severityValue }
            } ?: ExceptionSeverity.Undefined

        var message: String? = MagickExceptionHelper_Message(ptr)?.toKString()
        val description: String? = MagickExceptionHelper_Description(ptr)?.toKString()

        if (description.isNotNullOrEmpty()) {
            message += "($description)"
        }

        if (message.isNullOrEmpty()) {
            message = ""
        }

        val result = create(severity, message)

        createRelatedExceptions(ptr)?.let {
            result.setRelatedException(it)
        }

        return result
    }

    private fun createRelatedExceptions(ptr: CPointer<ExceptionInfo>): List<MagickException>? {
        val nestedCount = MagickExceptionHelper_RelatedCount(ptr)
        if (nestedCount == 0uL) {
            return null
        }

        val result = mutableListOf<MagickException>()
        for (i in 0uL until nestedCount) {
            MagickExceptionHelper_Related(ptr, i)?.let {
                result.add(createException(it))
            }
        }

        return result
    }

    private fun create(
        severity: ExceptionSeverity,
        message: String,
    ): MagickException =
        when (severity) {
            ExceptionSeverity.BlobWarning -> MagickBlobWarningException(message)
            ExceptionSeverity.CacheWarning -> MagickCacheWarningException(message)
            ExceptionSeverity.CoderWarning -> MagickCoderWarningException(message)
            ExceptionSeverity.ConfigureWarning -> MagickConfigureWarningException(message)
            ExceptionSeverity.CorruptImageWarning -> MagickCorruptImageWarningException(message)
            ExceptionSeverity.DelegateWarning -> MagickDelegateWarningException(message)
            ExceptionSeverity.DrawWarning -> MagickDrawWarningException(message)
            ExceptionSeverity.FileOpenWarning -> MagickFileOpenWarningException(message)
            ExceptionSeverity.ImageWarning -> MagickImageWarningException(message)
            ExceptionSeverity.MissingDelegateWarning -> MagickMissingDelegateWarningException(message)
            ExceptionSeverity.ModuleWarning -> MagickModuleWarningException(message)
            ExceptionSeverity.OptionWarning -> MagickOptionWarningException(message)
            ExceptionSeverity.PolicyWarning -> MagickPolicyWarningException(message)
            ExceptionSeverity.RegistryWarning -> MagickRegistryWarningException(message)
            ExceptionSeverity.ResourceLimitWarning -> MagickResourceLimitWarningException(message)
            ExceptionSeverity.StreamWarning -> MagickStreamWarningException(message)
            ExceptionSeverity.TypeWarning -> MagickTypeWarningException(message)

            ExceptionSeverity.BlobError -> MagickBlobErrorException(message)
            ExceptionSeverity.CacheError -> MagickCacheErrorException(message)
            ExceptionSeverity.CoderError -> MagickCoderErrorException(message)
            ExceptionSeverity.ConfigureError -> MagickConfigureErrorException(message)
            ExceptionSeverity.CorruptImageError -> MagickCorruptImageErrorException(message)
            ExceptionSeverity.DelegateError -> MagickDelegateErrorException(message)
            ExceptionSeverity.DrawError -> MagickDrawErrorException(message)
            ExceptionSeverity.FileOpenError -> MagickFileOpenErrorException(message)
            ExceptionSeverity.ImageError -> MagickImageErrorException(message)
            ExceptionSeverity.MissingDelegateError -> MagickMissingDelegateErrorException(message)
            ExceptionSeverity.ModuleError -> MagickModuleErrorException(message)
            ExceptionSeverity.OptionError -> MagickOptionErrorException(message)
            ExceptionSeverity.PolicyError -> MagickPolicyErrorException(message)
            ExceptionSeverity.RegistryError -> MagickRegistryErrorException(message)
            ExceptionSeverity.ResourceLimitError -> MagickResourceLimitErrorException(message)
            ExceptionSeverity.StreamError -> MagickStreamErrorException(message)
            ExceptionSeverity.TypeError -> MagickTypeErrorException(message)

            else ->
                if (severity.severity < ExceptionSeverity.Error.severity) {
                    MagickWarningException(message)
                } else {
                    MagickErrorException(message)
                }
        }
}
